package com.dev.rawgapps.data.local.datasource

import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface RawgLocalDataSource {
    fun getGameBySlag(slag:String): Flow<Result<Game>>
    suspend fun addFavoriteGame(game: Game)

    suspend fun deleteFavoriteGame(slug:String)
}