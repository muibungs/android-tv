package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val _typename: String? = "",
    val parentId: String? = "",
    val category_id: String? = "",
    val category_image: String? = "",
    val category_name: String? = "",
    val category_image_cover: String? = "",
    val category_video_group: String? = "",
    val category_description: String? = "",
    val category_video_cat: String? = "",
    val category_sort: String? = "",
    val path: String? = "",
    val content_bugaboointer_url: String? = ""
) : Parcelable