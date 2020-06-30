package com.example.myapplication.model

abstract class Detail(
    val itemId: String,
    val itemTitle: String
)

data class DetailData(
    val id: String,
    val title: String,
    val description: String,
    val descriptionUrl: String?,
    val thumbnail: String,
    val date: Long?,
    var shareUrl: String? = null,
    var isShowFullDescription: Boolean = false,
    var hasVideo: Boolean = false,
    var entryId: String? = null,
    var view: Int? = null,
    var isRelate: Boolean = false
    ) : Detail(id, title)

data class EpisodeTitle(
    val title: String = "Episode"
) : Detail(title, title)

data class Episode(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val date: Long,
    val videoList: String?
) : Detail(id, title)

data class Relate(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val date: Long,
    val entryId: String?
) : Detail(id, title)

data class EntryTitle(
    val title: String = "Related"
) : Detail(title, title)

data class Entry(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    var hasVideo: Boolean = false
) : Detail(id, title)