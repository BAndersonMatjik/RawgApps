package com.dev.rawgapps.data.remote.datasource

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface PageDataSourceFacade<T>{
    fun create(keyword: String):T
}


@ViewModelScoped
class GamePageDataSourceFacade @Inject constructor(private val rawgRemoteDataSource: RawgRemoteDataSource):PageDataSourceFacade<GamePagingDataSource>{
    override fun create(keyword: String): GamePagingDataSource {
        return GamePagingDataSource( rawgRemoteDataSource = rawgRemoteDataSource, query = keyword)
    }

}