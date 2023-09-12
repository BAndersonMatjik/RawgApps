package com.dev.rawgapps.feature.favoritegame

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev.rawgapps.common.BaseViewModel
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.GetFavoriteGamesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteGameViewModel @Inject constructor(
    private val getFavoriteGamesUsecase: GetFavoriteGamesUsecase
) : BaseViewModel<FavoriteGameViewModel.FavoriteGameEvent>() {
    private val _gameState: MutableStateFlow<PagingData<Game>> =
        MutableStateFlow(value = PagingData.empty())
    val gamesState: MutableStateFlow<PagingData<Game>> get() = _gameState
    init {
        onEvent(FavoriteGameEvent.GetFavoriteGames)
    }
    sealed class FavoriteGameEvent {
        object GetFavoriteGames : FavoriteGameEvent()
    }

    override fun onEvent(event: FavoriteGameEvent) {
        viewModelScope.launch {
            when (event) {
                is FavoriteGameEvent.GetFavoriteGames -> {
                    getFavoriteGames()
                }
                else -> {

                }
            }
        }
    }
    private suspend fun getFavoriteGames(){
        getFavoriteGamesUsecase().distinctUntilChanged().cachedIn(viewModelScope)
            .collectLatest {
                _gameState.value = it
            }
    }
}