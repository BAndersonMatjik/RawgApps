package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class AddFavoriteGameUsecaseImpl @Inject constructor(private val rawgRepository: RawgRepository) :
    AddFavoriteGameUsecase {
    override suspend fun invoke(game: Game) {
        withContext(Dispatchers.IO){
            if (!game.isFavorite){
                rawgRepository.addFavoriteGame(game)
            }else{
                rawgRepository.removeFavoriteGame(slug = game.slug)
            }
        }
    }
}