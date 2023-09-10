package com.dev.rawgapps.data.remote.datasource

import androidx.annotation.WorkerThread
import com.dev.rawgapps.common.DateFormatter
import com.dev.rawgapps.common.UrlUtils.createRequestUrl
import com.dev.rawgapps.data.remote.ApiRoutes
import com.dev.rawgapps.data.remote.model.GameDetailResponse
import com.dev.rawgapps.data.remote.model.GamesResponse
import com.dev.rawgapps.domain.Game
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgRemoteDataSourceImpl @Inject constructor(private val httpClient: HttpClient) :
    RawgRemoteDataSource {
    companion object {
        private const val TAG = "RawgRemoteDataSourceImp"
    }

    @WorkerThread
    override suspend fun getGames(page: Int, pageSize: Int, keyword: String): Result<List<Game>> {
        val url = createRequestUrl(ApiRoutes.ENDPOINT_GAMES) {
            it.apply {
                if (keyword.isNotBlank()) {
                    append("search", keyword)
                }
                append("page", page.toString())
                append("pageSize", pageSize.toString())
            }
        }
        val result = kotlin.runCatching {
            httpClient.get<GamesResponse>(url).let { gamesResponse ->
                gamesResponse.results?.map {
                    Game(
                        it.slug ?: "",
                        it.name ?: "",
                        genres = it.genres?.map { it.name ?: "" } ?: listOf(),
                        released = DateFormatter.formatDate(it.released ?: ""),
                        backgroundImage = it.backgroundImage ?: "",
                        description = "",
                        developer = ""
                    )
                } ?: listOf()
            }
        }
        result.exceptionOrNull()?.apply {
            Timber.e(this)
        }
        return result
    }

    @WorkerThread
    override suspend fun getGameDetail(slug: String): Result<Game> {
        val url = createRequestUrl(
            ApiRoutes.ENDPOINT_GAMES_DETAIL.replace(
                oldValue = "{slug}",
                newValue = slug
            )
        )
        val result = kotlin.runCatching {
            httpClient.get<GameDetailResponse>(url).let { response ->
                Game(
                    slug = response.slug ?: "",
                    name = response.name ?: "",
                    genres = response.genres?.map { it.name ?: "" } ?: listOf(),
                    released = DateFormatter.formatDate(response.released ?: ""),
                    backgroundImage = response.backgroundImage ?: "",
                    description = response.description?.replace("/<[^>]+>/g", "") ?: "",
                    developer = response.developers?.map { it.name ?: "" }?.joinToString() ?: ""
                )
            }
        }
        result.exceptionOrNull()?.apply {
            Timber.e(this)
        }
        return result
    }


}