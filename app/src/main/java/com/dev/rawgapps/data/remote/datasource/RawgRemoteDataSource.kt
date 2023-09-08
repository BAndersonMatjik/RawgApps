package com.dev.rawgapps.data.remote.datasource

import com.dev.rawgapps.domain.Game

interface RawgRemoteDataSource {
    suspend fun getGames(page:Int,pageSize:Int):  Result<List<Game>>
    suspend fun getGameDetail(slug:String):Result<Game>
}