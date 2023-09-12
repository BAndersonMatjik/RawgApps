package com.dev.rawgapps.data.local.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dev.rawgapps.common.Constants
import com.dev.rawgapps.common.JsonUtils
import com.dev.rawgapps.data.local.dao.FavoriteDao
import com.dev.rawgapps.data.local.model.FavoriteGameEntity
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgLocalDataSourceImpl @Inject constructor(
    private val dao: FavoriteDao
) : RawgLocalDataSource {
    @OptIn(ExperimentalSerializationApi::class)
    override fun getGameBySlag(slag: String): Flow<Result<Game>> = dao.findBySlag(slag).mapNotNull {
        Timber.tag(TAG).i("getGameBySlag: Fetch Local Data slag: %s", slag)
        val jsonContent = it?.jsonContent ?: ""
        if (jsonContent.isBlank()) {
            return@mapNotNull Result.failure<Game>(Exception("Data Not Found"))
        }
        val result = JsonUtils.json.decodeFromString<Game>(string = jsonContent)
        //Add Flag is this from local and tag as favorite
        Result.success(result.copy(isFavorite = true))
    }

    override suspend fun addFavoriteGame(game: Game) {
        val jsonContent = JsonUtils.json.encodeToString<Game>(value = game)
        dao.insertAll(FavoriteGameEntity(slag = game.slug, jsonContent = jsonContent))
    }

    override suspend fun deleteFavoriteGame(slug: String) {
        dao.deleteBySlag(slug)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getFavoriteGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(Constants.PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                dao.findAllPaged()
            }
        ).flow.map { it ->
            it.map {
                val result = JsonUtils.json.decodeFromString<Game>(string = it.jsonContent)
                result
            }
        }
    }

    companion object {
        private const val TAG = "RawgLocalDataSourceImpl"
    }
}