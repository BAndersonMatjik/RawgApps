package com.dev.rawgapps.domain.usecase

import androidx.paging.PagingData
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface GetFavoriteGamesUsecase {
    operator suspend fun invoke(): Flow<PagingData<Game>>
}
