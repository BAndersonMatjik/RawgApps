package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetDetailUsecaseImpl @Inject constructor(private val rawgRepository: RawgRepository) :
    GetDetailGameUsecase {
    override suspend operator fun invoke(slug: String): Flow<Result<Game>> {
        return flow {
            combine(rawgRepository.getGame(slug),rawgRepository.getGameFavorite(slug)){game,gameFavorite->
                if (gameFavorite==null){
                    emit(game)
                }else{
                    emit(gameFavorite)
                }
            }
        }
    }
}