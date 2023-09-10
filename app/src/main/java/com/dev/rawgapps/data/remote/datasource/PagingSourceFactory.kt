package com.dev.rawgapps.data.remote.datasource

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface PageDataSourceFacade<T>{
    fun create(vararg params:String):T
}


@ViewModelScoped
class GamePageDataSourceFacade @Inject constructor(private val rawgRemoteDataSource: RawgRemoteDataSource):PageDataSourceFacade<GamePagingDataSource>{
    override fun create(vararg params:String): GamePagingDataSource {
        return GamePagingDataSource( rawgRemoteDataSource = rawgRemoteDataSource, query = params[0])
    }

}