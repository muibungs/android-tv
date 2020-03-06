package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout

class CustomTextView(context: Context?,attr: AttributeSet) : RelativeLayout(context,attr) {

    init {
        initView()
    }

    fun initView(){
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view  = View.inflate(context , R.layout.menu_item,this)
    }



}