package com.dev.rawgapps.data.remote.datasource

import androidx.paging.PagingSource
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface RawgRemoteDataSource {
    suspend fun getGames(page:Int,pageSize:Int): Flow<PagingSource<Int, Game>>
    suspend fun getGameDetail(slug:String):Result<Game>
}