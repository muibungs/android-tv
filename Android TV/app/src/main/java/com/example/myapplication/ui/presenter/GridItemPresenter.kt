package com.example.myapplication.ui.presenter

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import com.example.myapplication.R

class GridItemPresenter() :
    Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = TextView(parent.context)
        val res = parent.resources
        val width = res.getDimensionPixelSize(R.dimen.grid_item_width)
        val height = res.getDimensionPixelSize(R.dimen.grid_item_height)
        view.layoutParams = ViewGroup.LayoutParams(width, height)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundColor(
            ContextCompat.getColor(
                parent.context,
                R.color.default_background
            )
        )
        view.setTextColor(Color.WHITE)
        view.gravity = Gravity.CENTER
//        val inflater = LayoutInflater.from(parent.context)
//        val view = ItemLandingBinding.inflate(inflater, parent, false)
//        val view: View = inflater.inflate(R.layout.item_landing, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        item: Any
    ) {
//        val binding = viewHolder.view as ItemLandingBinding
//        binding.tvLanding.text = item as String
        (viewHolder.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}

}