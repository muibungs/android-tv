package com.example.myapplication.ui.presenter

import android.content.Context
import android.view.ContextThemeWrapper
import android.widget.ImageView
import androidx.leanback.widget.ImageCardView
import com.example.myapplication.model.Card
import com.bumptech.glide.Glide
import com.example.myapplication.R

class ImageCardViewPresenter @JvmOverloads constructor(
    context: Context?,
    cardThemeResId: Int = R.layout.item_landing
) : AbstractCardPresenter<ImageCardView?>(
        ContextThemeWrapper(
            context,
            R.style.DefaultCardTheme
        )
    ) {

    override fun onCreateView(): ImageCardView {
        //        imageCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Clicked on ImageCardView", Toast.LENGTH_SHORT).show();
//            }
//        });
        return ImageCardView(context)
    }

    override fun onBindViewHolder(card: Card?, cardView: ImageCardView?) {
        cardView?.tag = card
        cardView?.titleText = card?.title
        cardView?.contentText = card?.description
        if (card?.localImageResourceName != null) {
            val resourceId: Int = context.resources
                .getIdentifier(
                    card.localImageResourceName,
                    "drawable", context.packageName
                )
            cardView?.mainImageView?.let {
                Glide.with(context)
                    .asBitmap()
                    .load(resourceId)
                    .into(it)
                it.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }
    }
}