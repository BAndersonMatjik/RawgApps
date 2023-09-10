package com.dev.rawgapps.data.local.datasource

import android.util.Log
import com.dev.rawgapps.common.JsonUtils
import com.dev.rawgapps.data.local.dao.FavoriteDao
import com.dev.rawgapps.data.local.model.FavoriteGameEntity
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgLocalDataSourceImpl @Inject constructor(
    private val dao: FavoriteDao
) : RawgLocalDataSource {
    @OptIn(ExperimentalSerializationApi::class)
    override fun getGameBySlag(slag: String): Flow<Result<Game>> = dao.findBySlag(slag).mapNotNull {
        Log.i(TAG, "getGameBySlag: Fetch Local Data slag: $slag")
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

    override suspend fun deleteFavoriteGame(slug:String) {
        dao.deleteBySlag(slug)
    }

    companion object {
        private const val TAG = "RawgLocalDataSourceImpl"
    }
}