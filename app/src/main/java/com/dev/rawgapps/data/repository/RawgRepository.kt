package com.dev.rawgapps.data.repository

import androidx.paging.PagingData
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface RawgRepository {
    suspend fun getGames(): Flow<PagingData<Game>>
}