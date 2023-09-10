package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.domain.Game

interface AddFavoriteGameUsecase {
    operator suspend fun invoke(game: Game)
}

