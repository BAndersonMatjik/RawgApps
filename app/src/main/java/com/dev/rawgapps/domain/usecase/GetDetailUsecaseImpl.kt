package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetDetailUsecaseImpl @Inject constructor(private val rawgRepository: RawgRepository) :
    GetDetailGameUsecase {
    override suspend fun invoke(slug: String): Flow<Result<Game>> {
        return flow {
            emit(rawgRepository.getGame(slug))
        }
    }
}