package com.dev.rawgapps.feature.game

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev.rawgapps.common.BaseViewModel
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.GetGamesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val getGamesUsecase: GetGamesUsecase) :
    BaseViewModel<GameViewModel.GameEvent>() {

    private val _gameState: MutableStateFlow<PagingData<Game>> =
        MutableStateFlow(value = PagingData.empty())
    val gamesState: MutableStateFlow<PagingData<Game>> get() = _gameState

    init {
        onEvent(GameEvent.GetGames)
    }

    override fun onEvent(event: GameEvent) {
        viewModelScope.launch {
            when (event) {
                is GameEvent.GetGames -> {
                    getGamesAllOrSearch("")
                }

                is GameEvent.SearchGames -> {
                    getGamesAllOrSearch(keyword = event.keyword)
                }

                else -> {
                    TODO("NEED HANDLE")
                }
            }
        }
    }

    private suspend fun getGamesAllOrSearch(keyword: String) {
        getGamesUsecase.invoke(keyword).distinctUntilChanged().cachedIn(viewModelScope)
            .collectLatest {
                _gameState.value = it
            }
    }

    sealed interface GameEvent {
        object GetGames : GameEvent
        data class SearchGames(val keyword: String) : GameEvent
    }

}