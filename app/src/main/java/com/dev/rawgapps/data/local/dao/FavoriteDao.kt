package com.dev.rawgapps.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg game: Game)

    @Delete
    fun delete(game:Game)

    @Query("SELECT * FROM favorite_game WHERE slag = :slag")
    suspend fun findBySlag(slag:String): Flow<Game>
}