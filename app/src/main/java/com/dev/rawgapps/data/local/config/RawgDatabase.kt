package com.dev.rawgapps.data.local.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.rawgapps.data.local.config.RawgDatabase.Companion.DB_VERSION
import com.dev.rawgapps.data.local.dao.FavoriteDao
import com.dev.rawgapps.data.local.model.FavoriteGameEntity

@Database(entities = [FavoriteGameEntity::class], exportSchema = false, version = DB_VERSION)
abstract class RawgDatabase : RoomDatabase() {
    companion object{
        const val DB_VERSION = 6
        const val DB_NAME = "rawg_database"
    }

    abstract fun favoriteDao():FavoriteDao

}