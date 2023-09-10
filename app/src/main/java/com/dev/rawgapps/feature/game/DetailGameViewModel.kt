package com.dev.rawgapps.feature.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dev.rawgapps.common.BaseViewModel
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(
    private val getDetailGameUseCase: GetDetailGameUsecase,
    private val addFavoriteGameUsecase: AddFavoriteGameUsecase
) : BaseViewModel<DetailGameViewModel.DetailGameEvent>() {
    private val _uiState = mutableStateOf(DetailGameViewState())
    val uiState: State<DetailGameViewState> get() = _uiState
    override fun onEvent(event: DetailGameEvent) {
        viewModelScope.launch {
            when (event) {
                //Handling Ui Event GetDetailGame
                is DetailGameEvent.GetDetailGame -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                    getDetailGameUseCase(event.slug).collectLatest {
                        it.fold(onSuccess = {
                            _uiState.value = _uiState.value.copy(isLoading = false, game = it, showFavoriteIcon = true)
                        }, onFailure = {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                errorFetchDetailGame = it.message.toString()
                            )
                        }
                        )
                    }
                }

                is DetailGameEvent.Init -> {
                    _uiState.value = _uiState.value.copy(game = event.game)
                }

                is DetailGameEvent.SaveIsFavorite->{
                    Timber.tag(Companion.TAG).d("onEvent: SaveIsFavorite")
                    addFavoriteGameUsecase(game = _uiState.value.game)
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
        val game: Game = Game(
            slug = "",
            name = "",
            genres = listOf(),
            released = "",
            backgroundImage = "",
            description = "",
            developer = ""
        ),
        val showFavoriteIcon:Boolean = false
    )

    companion object {
        private const val TAG = "DetailGameViewModel"
    }

}