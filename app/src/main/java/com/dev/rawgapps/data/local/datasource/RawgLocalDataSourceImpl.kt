package com.dev.rawgapps.data.local.datasource

import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

class RawgLocalDataSourceImpl:RawgLocalDataSource {
    override fun getGameBySlag(slag: String): Flow<Game> {
        TODO("Not yet implemented")
    }
}