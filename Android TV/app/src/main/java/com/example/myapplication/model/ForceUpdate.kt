package com.bugaboo.dev.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForceUpdate(
    val android: Platform = Platform(),
    val ios: Platform = Platform()
) : Parcelable

@Parcelize
data class Platform(
    val forceUnderVersion: String = "0",
    val nonForceUnderVersion: String = "0",
    val remindingDate: Int = 0
) : Parcelable

enum class ForceUpdateType(val type: String){
    FORCE_UPDATE("FORCE_UPDATE"),
    JUST_REMIND("JUST_REMIND"),
    NONE("NONE")
}