package com.dev.rawgapps.data.remote.datasource

import androidx.annotation.WorkerThread
import com.dev.rawgapps.common.UrlUtils.createRequestUrl
import com.dev.rawgapps.data.remote.ApiRoutes
import com.dev.rawgapps.data.remote.model.GamesResponse
import com.dev.rawgapps.domain.Game
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgRemoteDataSourceImpl @Inject constructor(private val httpClient: HttpClient):RawgRemoteDataSource {
    @WorkerThread
    override suspend fun getGames(page: Int, pageSize: Int,keyword:String): Result<List<Game>> {
        val url = createRequestUrl(ApiRoutes.ENDPOINT_GAMES) {
            it.apply {
                if (keyword.isNotBlank()){
                    append("search",keyword)
                }
                append("page", page.toString())
                append("pageSize", pageSize.toString())
            }
        }
        val result = kotlin.runCatching {
            httpClient.get<GamesResponse>(url).let {gamesResponse->
                gamesResponse.results?.map {
                    Game(it.slug?:"",it.name?:"", genre = it.genres?.map { it.name?:"" }?: listOf(), released = it.released?:"", backgroundImage = it.backgroundImage?:"", description = "")
                }?: listOf()
            }
        }
        return result
    }
    @WorkerThread
    override suspend fun getGameDetail(slug: String): Result<Game> {
        TODO("Not yet implemented")
    }


}