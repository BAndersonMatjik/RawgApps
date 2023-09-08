package com.dev.rawgapps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev.rawgapps.common.Constants
import com.dev.rawgapps.data.remote.datasource.GamePagingDataSource
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class RawgRepositoryImpl @Inject constructor(private val gamePagingDataSource: GamePagingDataSource):RawgRepository {
    override suspend fun getGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(Constants.PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                gamePagingDataSource
            }
        ).flow
    }
}