package com.example.myapplication.util

import android.content.Context

class Contextor {
    private var instance: Contextor? = null

    private var mContext: Context? = null

    fun getInstance(): Contextor? {
        if (instance == null) instance = Contextor()
        return instance
    }

    fun init(context: Context?) {
        mContext = context
    }

    fun getContext(): Context? {
        return mContext
    }
}