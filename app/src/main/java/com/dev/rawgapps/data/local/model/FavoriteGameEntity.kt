package com.dev.rawgapps.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_game", indices = [Index(value = ["slag"], unique = true)])
data class FavoriteGameEntity(
    @PrimaryKey(true)
    val id:String?=null,
    @ColumnInfo(name = "slag")
    val slag:String,
    @ColumnInfo(name = "json_content")
    val jsonContent:String
)
