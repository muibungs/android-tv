package com.example.myapplication.model

import java.text.SimpleDateFormat
import java.util.*

abstract class LiveSport(
    val itemId: String,
    val itemTitle: String?
)

data class ProgramTitle(
    val title: String = "ตารางถ่ายทอดสด"
) : LiveSport(title, title)

data class Program(
    val path: String?,
    val entryId: String,
    val title: String?,
    val description: String?,
    val thumb: String?,
    val shareUrl: String?,
    val hasMatch: Boolean,
    val hasVideo: Boolean = false
) : LiveSport(entryId, title)

data class Match(
    val programId: String,
    val entryId: String,
    val title: String?,
    val description: String?,
    val thumb: String?,
    val thumbVertical: String?,
    val thumbHorizontal: String?,
    val contentUrl: String?,
    val homeTeam: String?,
    val homeTeamLogo: String?,
    val awayTeam: String?,
    val awayTeamLogo: String?,
    val startTime: Long,
    val endTime: Long,
    val path: String?
) : LiveSport(entryId, title) {

    fun isDateShow(beforeMatch: Match): Boolean {
        val dateFormat = SimpleDateFormat.getDateInstance()
        val current = dateFormat.format(Date(this.startTime * 1000L))
        val before = dateFormat.format(Date(beforeMatch.startTime * 1000L))
        return current != before
    }

    fun isLiveMatch(): Boolean {
        return (startTime * 1000L) < System.currentTimeMillis() && System.currentTimeMillis() < (endTime * 1000L)
    }
}