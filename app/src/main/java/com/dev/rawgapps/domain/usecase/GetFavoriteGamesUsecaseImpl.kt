package com.dev.rawgapps.domain.usecase

import androidx.paging.PagingData
import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetFavoriteGamesUsecaseImpl @Inject constructor(private val repositoryImpl: RawgRepository):
    GetFavoriteGamesUsecase {
    override suspend fun invoke(): Flow<PagingData<Game>> = repositoryImpl.getFavoriteGames()
}