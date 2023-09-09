package com.dev.rawgapps.domain.usecase

import androidx.paging.PagingData
import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Provider

@ViewModelScoped
class GetGamesUsecaseImpl @Inject constructor(private val rawgRepository: Provider<RawgRepository>):GetGamesUsecase {
    private var lastKeyword = ""
    override suspend fun invoke(keyword: String): Flow<PagingData<Game>> {
       return rawgRepository.get().getGames(keyword)
    }
}