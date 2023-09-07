package com.dev.rawgapps.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_game")
data class FavoriteGameEntity(
    @PrimaryKey
    val id:String,
    val jsonContent:String,
    val createdTm:String
)
