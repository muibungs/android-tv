package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.leanback.app.HeadersSupportFragment

class MenuFragment : HeadersSupportFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"onActivityCreated")





    }


    override fun setOnHeaderClickedListener(listener: OnHeaderClickedListener?) {
        super.setOnHeaderClickedListener(listener)

    }

    companion object {
        private val TAG = "MenuFragment"

    }
}