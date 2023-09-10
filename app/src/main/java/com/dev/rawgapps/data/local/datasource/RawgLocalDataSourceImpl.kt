package com.dev.rawgapps.data.local.datasource

import com.dev.rawgapps.common.JsonUtils
import com.dev.rawgapps.data.local.dao.FavoriteDao
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgLocalDataSourceImpl @Inject constructor(
    private val dao: FavoriteDao
):RawgLocalDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override fun getGameBySlag(slag: String): Flow<Game> =dao.findBySlag(slag).map {
        JsonUtils.json.decodeFromString<Game>(string = it.jsonContent)
    }
}