package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class CardRow {
    @SerializedName("type")
    val type = TYPE_DEFAULT
    // Used to determine whether the row shall use shadows when displaying its cards or not.
    @SerializedName("shadow")
    private val mShadow = true
    @SerializedName("title")
    val title: String? = null
    @SerializedName("cards")
    val cards: List<Card>? = null

    fun useShadow(): Boolean {
        return mShadow
    }

    companion object {
        // default is a list of cards
        const val TYPE_DEFAULT = 0
        // section header
        const val TYPE_SECTION_HEADER = 1
        // divider
        const val TYPE_DIVIDER = 2
    }
}