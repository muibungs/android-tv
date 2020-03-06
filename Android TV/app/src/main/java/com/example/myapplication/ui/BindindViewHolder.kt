package com.example.myapplication.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding = DataBindingUtil.bind<ViewDataBinding>(itemView)
}