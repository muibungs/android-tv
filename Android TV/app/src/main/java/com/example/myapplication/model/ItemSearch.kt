package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemSearch(
    val parentId: String? = "",
    val category_id: String = "",
    val category_image: String? = "",
    val category_name: String? = "",
    val category_image_cover: String? = "",
    val category_video_group: String? = "",
    val category_video_cat: String? = "",
    val category_description: String? = "",
    val category_sort: String? = "",
    val category_site: String? = "",
    val category_type: String? = "",
    val path: String? = "",
    val content_bugaboointer_url: String? = "",
    val token: String? = "",
    val entryId: String? = "",
    val entryDate: Long = 0L,
    val views: Int = 0,
    val title: String = "",
    val description: String = "",
    val channel_name: String = "",
    val content_url: String = "",
    val description_url: String = "",
    val group: String = "",
    val link: String = "",
    val bugaboo_inter_link: String = "",
    val tag: String = "",
    val thumb: String = "",
    val thumb_vertical: String? = "",
    val thumb_horizontal: String? = "",
    val thumb_cover: String? = "",
    val rating: String? = "",
    val site: String? = "",
    val type: Int = -1,
    val bugaboo_teaser_id: String = "",
    val bugaboo_video_group: String = "",
    val bugaboo_video_id: String = "",
    val bugaboo_drama_tag: String = "",
    val bugaboo_site: String = "",
    val ch7_cat_id: String = ""
) : Parcelable