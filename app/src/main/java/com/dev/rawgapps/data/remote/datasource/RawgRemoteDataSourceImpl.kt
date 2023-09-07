package com.dev.rawgapps.data.remote.datasource

import androidx.paging.PagingSource
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgRemoteDataSourceImpl @Inject constructor():RawgRemoteDataSource {
    override suspend fun getGames(page: Int, pageSize: Int): Flow<PagingSource<Int, Game>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGameDetail(slug: String): Result<Game> {
        TODO("Not yet implemented")
    }
}