package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import timber.log.Timber
import javax.inject.Inject

@ViewModelScoped
class GetDetailUsecaseImpl @Inject constructor(private val rawgRepository: RawgRepository) :
    GetDetailGameUsecase {
    companion object {
        private const val TAG = "GetDetailUsecaseImpl"
    }

    override suspend operator fun invoke(slug: String): Flow<Result<Game>> {
        return combine(
            rawgRepository.getGameFavorite(slug).distinctUntilChanged(),
            rawgRepository.getGame(slug)
        ) { gameFavorite,game ->
            Timber.d("invoke: $game --- $gameFavorite")
            if (gameFavorite.isFailure) {
                game
            } else {
                gameFavorite
            }
        }
    }
}