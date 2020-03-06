package com.example.myapplication.model

import android.content.Context
import android.graphics.Color
import android.util.Log
import com.google.gson.annotations.SerializedName
import java.net.URI
import java.net.URISyntaxException

class Card {
    @SerializedName("title")
    var title = ""
    @SerializedName("description")
    var description = ""
    @SerializedName("extraText")
    var extraText = ""
    @SerializedName("card")
    var imageUrl: String? = null
    @SerializedName("footerColor")
    private var mFooterColor: String? = null
    @SerializedName("selectedColor")
    private var mSelectedColor: String? = null
    @SerializedName("localImageResource")
    var localImageResourceName: String? = null
    @SerializedName("footerIconLocalImageResource")
    var footerLocalImageResourceName: String? = null
        set
    @SerializedName("type")
    var type: Type? = null
    @SerializedName("id")
    var id = 0
    @SerializedName("width")
    var width = 0
    @SerializedName("height")
    var height = 0

    val footerColor: Int
        get() = if (mFooterColor == null) -1 else Color.parseColor(mFooterColor)

    fun setFooterColor(footerColor: String?) {
        mFooterColor = footerColor
    }

    val selectedColor: Int
        get() = if (mSelectedColor == null) -1 else Color.parseColor(mSelectedColor)

    fun setSelectedColor(selectedColor: String?) {
        mSelectedColor = selectedColor
    }

    val imageURI: URI?
        get() = if (imageUrl == null) null else try {
            URI(imageUrl)
        } catch (e: URISyntaxException) {
            Log.d("URI exception: ", imageUrl)
            null
        }

    fun getLocalImageResourceId(context: Context): Int {
        return context.resources.getIdentifier(
            localImageResourceName, "drawable",
            context.packageName
        )
    }

    enum class Type {
        MOVIE_COMPLETE, MOVIE, MOVIE_BASE, ICON, SQUARE_BIG, SINGLE_LINE, GAME, SQUARE_SMALL, DEFAULT, SIDE_INFO, SIDE_INFO_TEST_1, TEXT, CHARACTER, GRID_SQUARE, VIDEO_GRID
    }
}
