package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardItem (
    val type : String? = "",
    val title : String? = "",
    val imageUrl : String? = ""
    ) : Parcelable
