package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EntryItem(
    val entryId: Long = 0L,
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
    val cate_labels: List<String> = listOf(),
    val thumb: String = "",
    val thumb_vertical: String? = "",
    val thumb_horizontal: String? = "",
    val thumb_cover: String? = "",
    val logo: String? = "",
    val rating: String? = "",
    val site: String = "",
    val type: Int = -1,
    val actor: String = "",
    val author: String = "",
    val tv_writer: String = "",
    val director: String = "",
    val company: String = "",
    val category_onair: String = "",
    val show_datetime_onair: String = "",
    val bugaboo_teaser_id: String = "",
    val bugaboo_video_group: String = "",
    val bugaboo_video_id: String = "",
    val bugaboo_drama_tag: String = "",
    val bugaboo_site: String = "",
    val ch7_cat_id: String = "",
    val fullname: String = "",
    val nickname: String = "",
    val tag_star_news: String = "",
    val instagram_link: String = "",
    val drama_dateonair: Long? = 0L,
    val gallery_thumb_0: String? = "",
    val gallery_thumb_1: String? = "",
    val gallery_thumb_2: String? = "",
    val written: String = "",
    val pronounce: String = "",
    val meaning: String = "",
    val url: String = ""
) : Parcelable
