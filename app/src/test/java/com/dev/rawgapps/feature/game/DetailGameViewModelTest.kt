package com.dev.rawgapps.feature.game

import app.cash.turbine.test
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailGameViewModelTest {
    private lateinit var viewModel: DetailGameViewModel
    private val getDetailGameUseCase: GetDetailGameUsecase = mockk()
    private val addFavoriteGameUsecase: AddFavoriteGameUsecase = mockk()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeGame = Game(
        slug = "test",
        name = "Test Game",
        genres = listOf("Horro", "RPG"),
        released = "",
        backgroundImage = "http://image.com",
        description = "",
        developer = "",
        isFavorite = false
    )

    private val fakeGameResponse = Game(
        slug = "test",
        name = "Test Game",
        genres = listOf("Horro", "RPG"),
        released = "Sep 2023, 12",
        backgroundImage = "http://image.com",
        description = "this is test game",
        developer = "BAM",
        isFavorite = false
    )

    @Before
    fun setup() {
        viewModel = spyk(
            DetailGameViewModel(getDetailGameUseCase, addFavoriteGameUsecase),
            recordPrivateCalls = true
        )
    }

    @Test
    fun given_slug_when_get_detail_game_then_success() {
        coEvery {
            getDetailGameUseCase("test")
        } returns flow {
            //scenario delay network / local request
            emit(Result.success(fakeGameResponse))
        }
        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
            viewModel.uiState.test {
                //check showfavorite icon not get show when data not complete
                awaitItem().apply {
                    assertEquals(false, this.showFavoriteIcon)
                }
            }
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.GetDetailGame(slug = fakeGame.slug))
            viewModel.uiState.test {
                //show showfavorite icon show when complete fetch from remote / local
                awaitItem().apply {
                    println(this)
                    assertEquals(true, this.showFavoriteIcon)
                    //check is favorite || same with saved in local
                    assertEquals(false,this.game.isFavorite)
                }
            }
        }
    }

    fun mock_success_called(isLocalSource:Boolean=false) {
        coEvery {
            getDetailGameUseCase("test")
        } returns flow {
            //scenario delay network / local request
            emit(Result.success(fakeGameResponse.copy(isFavorite = isLocalSource)))
        }
    }
    @Test
    fun given_slug_when_get_detail_game_local_then_success() {
        mock_success_called(true)
        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
            viewModel.uiState.test {
                //check showfavorite icon not get show when data not complete
                awaitItem().apply {
                    org.junit.Assert.assertEquals(false, this.showFavoriteIcon)
                }
            }
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.GetDetailGame(slug = fakeGame.slug))
            viewModel.uiState.test {
                //show showfavorite icon show when complete fetch from remote / local
                awaitItem().apply {
                    println(this)
                    org.junit.Assert.assertEquals(true, this.showFavoriteIcon)
                    //check is favorite || same with saved in local
                    assertEquals(true,this.game.isFavorite)
                }
            }
        }
    }

    @Test
    fun given_slug_when_get_detail_game_local_or_remote_then_error() {
        coEvery {
            getDetailGameUseCase("test")
        } returns flow {
            //scenario delay network / local request
            emit(Result.failure(Exception("Failed Get Network")))
        }
        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
            viewModel.uiState.test {
                //check showfavorite icon not get show when data not complete
                awaitItem().apply {
                    org.junit.Assert.assertEquals(false, this.showFavoriteIcon)
                }
            }
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.GetDetailGame(slug = fakeGame.slug))
            viewModel.uiState.test {
                //show error failed fetch data
                awaitItem().apply {
                    println(this)
                    Truth.assertThat(this.isLoading).isFalse()
                    Truth.assertThat(this.errorFetchDetailGame).isEqualTo("Failed Get Network")
                }
            }
        }
    }

    @Test
    fun when_save_game_favorite_then_confirm_save_game(){
        mock_success_called(isLocalSource = false)
        coEvery {
            addFavoriteGameUsecase(fakeGameResponse)
        }returns true

        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.GetDetailGame(slug = fakeGame.slug))
            viewModel.uiState.test {
                awaitItem().apply {
                    println(this)
                }
            }
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.SaveIsFavorite)
            viewModel.uiState.test {
                awaitItem().apply {
                    println(this)
                    Truth.assertThat(this.isLoading).isEqualTo(false)
                    Truth.assertThat(this.messageToast).isEqualTo("Added to Favorite Game")
                }
            }
        }
    }


    @Test
    fun when_save_game_favorite_then_confirm_remove_game(){
        mock_success_called(isLocalSource = false)
        coEvery {
            addFavoriteGameUsecase(fakeGameResponse)
        }returns false

        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.GetDetailGame(slug = fakeGame.slug))
            viewModel.uiState.test {
                awaitItem().apply {
                    println(this)
                }
            }
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.SaveIsFavorite)
            viewModel.uiState.test {
                awaitItem().apply {
                    println(this)
                    Truth.assertThat(this.isLoading).isEqualTo(false)
                    Truth.assertThat(this.messageToast).isEqualTo("Deleted from Favorite Game")
                }
            }
        }
    }

    @Test
    fun when_save_game_favorite_then_return_exception(){
        mock_success_called(isLocalSource = false)
        coEvery {
            addFavoriteGameUsecase(fakeGameResponse)
        }throws NullPointerException("null on property x")

        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.GetDetailGame(slug = fakeGame.slug))
            viewModel.uiState.test {
                awaitItem().apply {
                    println(this)
                }
            }
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.SaveIsFavorite)
            viewModel.uiState.test {
                awaitItem().apply {
                    println(this)
                    Truth.assertThat(this.isLoading).isEqualTo(false)
                    Truth.assertThat(this.messageToast).isEqualTo("Error null on property x")
                }
            }
        }
    }
}