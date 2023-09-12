package com.dev.rawgapps.feature.game

import androidx.lifecycle.viewModelScope
import com.dev.rawgapps.common.BaseViewModel
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(
    private val getDetailGameUseCase: GetDetailGameUsecase,
    private val addFavoriteGameUsecase: AddFavoriteGameUsecase
) : BaseViewModel<DetailGameViewModel.DetailGameEvent>() {
    private val _uiState = MutableStateFlow(DetailGameViewState())
    val uiState: StateFlow<DetailGameViewState> get() = _uiState
    override fun onEvent(event: DetailGameEvent) {
        viewModelScope.launch {
            when (event) {
                //Handling Ui Event GetDetailGame
                is DetailGameEvent.GetDetailGame -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                    getDetailGameUseCase(event.slug).distinctUntilChanged().collectLatest {
                        it.fold(onSuccess = {response->
                            _uiState.update {
                                it.copy(isLoading = false, game = response, showFavoriteIcon = true)
                            }
                            Timber.d("uiState "+uiState.value.toString())
                        }, onFailure = {exception->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorFetchDetailGame = exception.message.toString()
                                )
                            }
                            Timber.d("uiState "+uiState.value.toString())
                        }
                        )
                    }
                }

                is DetailGameEvent.Init -> {
                    _uiState.update {
                        it.copy(
                            game = event.game
                        )
                    }
                }

                is DetailGameEvent.SaveIsFavorite -> {
                    Timber.tag(Companion.TAG)
                        .d("onEvent: SaveIsFavorite ${_uiState.value.game.isFavorite}")
                    kotlin.runCatching {
                        addFavoriteGameUsecase(game = _uiState.value.game)
                    }.onFailure {exception->
                       _uiState.update {
                           _uiState.value.copy(
                               isLoading = false,
                               messageToast = "Error ${exception.message}"
                           )
                       }
                    }.onSuccess {
                        val message = if (it) {
                            "Added to Favorite Game"
                        } else {
                            "Deleted from Favorite Game"
                        }
                        _uiState.updateAndGet {
                            it.copy(isLoading = false, messageToast = message)
                        }
                    }
                }

                else -> {}
            }
        }
    }

    sealed interface DetailGameEvent {
        data class Init(val game: Game) : DetailGameEvent
        data class GetDetailGame(val slug: String) : DetailGameEvent
        object SaveIsFavorite : DetailGameEvent
    }

    data class DetailGameViewState(
        val isLoading: Boolean = false,
        val errorFetchDetailGame: String = "",
        val messageToast: String = "",
        val game: Game = Game(
            slug = "",
            name = "",
            genres = listOf(),
            released = "",
            backgroundImage = "",
            description = "",
            developer = ""
        ),
        val showFavoriteIcon: Boolean = false
    )

    companion object {
        private const val TAG = "DetailGameViewModel"
    }

}