package com.example.myapplication.ui.presenter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.myapplication.R
import com.example.myapplication.model.Category
import com.example.myapplication.util.loadImage
import timber.log.Timber

class CardCategoryPresenter(private val context: Context) : Presenter(){
    class ViewHolder(view: View) : Presenter.ViewHolder(view) {
        var category: Category? = null
        val cardView: ImageCardView
        val defaultCardImage: Drawable

        private val mContext: Context

        fun updateCardViewImage(uri: String) {
            cardView.mainImageView.loadImage(uri, R.mipmap.place_holder_portrait)
        }

        init {
            cardView = view as ImageCardView
            mContext = view.getContext()
            defaultCardImage = mContext.resources.getDrawable(R.drawable.movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val context = parent.context

        val cardView = ImageCardView(context)
        cardView.cardType = BaseCardView.CARD_TYPE_MAIN_ONLY
        cardView.infoVisibility = BaseCardView.CARD_REGION_VISIBLE_ALWAYS
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val category = item as Category
        (viewHolder as ViewHolder).category = category

//        val text = viewHolder.cardView.findViewById<View>(R.id.title_text) as TextView
//        val textDesc = viewHolder.cardView.findViewById<View>(R.id.content_text) as TextView
//        val face = Typeface.createFromAsset(viewHolder.view.context.assets, "font/sukhumvitset_medium.ttf")
//
//        text.typeface = face
//        textDesc.typeface = face
//        text.text = category.category_name
//
//        val date = category.category_description
//        viewHolder.cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)

        category.category_image_cover?.let {
            Timber.d("thumb $it")
            viewHolder.updateCardViewImage(it);

        }
        viewHolder.cardView.setOnClickListener {
            Toast.makeText(context,"${category.category_name}",Toast.LENGTH_LONG).show()
        }

    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {}

    companion object {
        private val TAG = CardCategoryPresenter::class.java.simpleName
        private const val CARD_WIDTH = 400
        private const val CARD_HEIGHT = 600
    }
}