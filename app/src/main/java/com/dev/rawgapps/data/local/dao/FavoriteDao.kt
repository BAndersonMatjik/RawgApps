package com.dev.rawgapps.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.rawgapps.data.local.model.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg game: FavoriteGameEntity)

    @Delete
    fun delete(game:FavoriteGameEntity)

    @Query("SELECT * FROM favorite_game WHERE slag = :slag")
    fun findBySlag(slag:String): Flow<FavoriteGameEntity?>

    @Query("SELECT * FROM favorite_game ORDER BY id ASC")
    fun findAllPaged(): PagingSource<Int,FavoriteGameEntity>

    @Query("DELETE FROM favorite_game WHERE slag = :slug")
    fun deleteBySlag(slug: String)
}