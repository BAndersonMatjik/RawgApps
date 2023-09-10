package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface GetDetailGameUsecase {
    suspend operator fun invoke(slug:String): Flow<Result<Game>>
}