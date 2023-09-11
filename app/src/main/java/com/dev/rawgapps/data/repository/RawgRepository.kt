package com.dev.rawgapps.data.repository

import androidx.paging.PagingData
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface RawgRepository {
    suspend fun getGames(keyword:String): Flow<PagingData<Game>>

    suspend fun getGame(slug:String):Flow<Result<Game>>

    suspend fun getGameFavorite(slug:String):Flow<Result<Game>>

    suspend fun addFavoriteGame(game: Game)
    suspend fun removeFavoriteGame(slug: String)
    suspend fun getFavoriteGames():Flow<PagingData<Game>>
}