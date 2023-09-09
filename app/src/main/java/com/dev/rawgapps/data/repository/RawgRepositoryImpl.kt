package com.dev.rawgapps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev.rawgapps.common.Constants
import com.dev.rawgapps.data.remote.datasource.GamePagingDataSource
import com.dev.rawgapps.data.remote.datasource.PageDataSourceFacade
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RawgRepositoryImpl @Inject constructor(private val gamePageDataSourceFacade: PageDataSourceFacade<GamePagingDataSource>):RawgRepository {
    override suspend fun getGames(keyword:String): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(Constants.PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                gamePageDataSourceFacade.create(keyword)
            }
        ).flow
    }
}