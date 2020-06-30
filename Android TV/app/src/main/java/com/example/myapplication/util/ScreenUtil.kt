package com.example.myapplication.util

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

class ScreenUtils() {
    private lateinit var mContext: Context
    val screenWidth: Int
        get() {
            val wm =
                mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x
        }

    val screenHeight: Int
        get() {
            val wm =
                mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.y
        }

    fun getInstance(): ScreenUtils? {
        if (instance == null) instance = ScreenUtils()
        return instance
    }

    /**
     *
     * @param width
     * // Ex 100x75
     * @return
     */
    fun getScreenHeight_4x3(width: Int): Int {
        return width * 3 / 4
    }

    /**
     *
     * @param width
     * // Ex 100x133
     * @return
     */
    fun getScreenHeight_3x4(width: Int): Int {
        return width * 4 / 3
    }

    /**
     *
     * @param width
     * // Ex 100x150
     * @return
     */
    fun getScreenHeight_2x3(width: Int): Int {
        return width * 3 / 2
    }

    /**
     *
     * @param width
     * // Ex 100x66
     * @return
     */
    fun getScreenHeight_3x2(width: Int): Int {
        return width * 2 / 3
    }

    /**
     *
     * @param width
     * Ex 100x56
     * @return
     */
    fun getScreenHeight_16x9(width: Int): Int {
        return width * 9 / 16
    }

    /**
     *
     * @param width
     * Ex 100x177
     * @return
     */
    fun getScreenHeight_9x16(width: Int): Int {
        return width * 16 / 9
    }

    companion object {
        var instance: ScreenUtils? = null
            get() {
                if (field == null) field =
                    ScreenUtils()
                return field
            }
            private set
    }

    init {
        Contextor().getInstance()?.getContext()?.let {
            mContext = it
        }
    }
}