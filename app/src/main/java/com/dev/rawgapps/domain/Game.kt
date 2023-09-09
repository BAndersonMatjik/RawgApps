package com.dev.rawgapps.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Game(
    val slug:String,
    val name:String,
    val genre:List<String>,
    val released:String,
    val backgroundImage:String,
    val description:String
):Parcelable