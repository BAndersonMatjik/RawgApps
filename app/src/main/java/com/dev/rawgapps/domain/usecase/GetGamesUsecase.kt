package com.dev.rawgapps.domain.usecase

import androidx.paging.PagingData
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface GetGamesUsecase {
    suspend operator fun invoke(): Flow<PagingData<Game>>
}