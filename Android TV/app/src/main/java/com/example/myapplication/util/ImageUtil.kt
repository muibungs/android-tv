package com.example.myapplication.util

import android.content.Context
import com.example.myapplication.Utils

object ImageUtil {

    fun getRealPath(context: Context, imageUrl: String): String? {
        val width = Utils.getDisplaySize(context)?.x?.div(2)
        val height = ScreenUtils().getInstance()?.getScreenHeight_2x3(width!!)
        return Utils.getRealImageUrl(
            "https://api.bugaboo.tv/images/thumb/index.php",
            imageUrl,
            width.toString(),
            height.toString(),
            "no"
        )
    }
}