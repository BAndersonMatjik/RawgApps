package com.dev.rawgapps.feature.favoritegame

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import app.cash.turbine.test
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.GetFavoriteGamesUsecase
import com.dev.rawgapps.feature.game.MainDispatcherRule
import com.dev.rawgapps.feature.game.TestDiffCallback
import com.dev.rawgapps.feature.game.TestListCallback
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteGameViewModelTest {
    @get:Rule(order = 0)
    val mainDispatcherRule = MainDispatcherRule()

    private val getFavoriteGamesUsecase: GetFavoriteGamesUsecase = mockk(relaxed = true)

    private lateinit var viewModel: FavoriteGameViewModel

    private val differ = AsyncPagingDataDiffer(
        diffCallback = TestDiffCallback<Game>(),
        updateCallback = TestListCallback(),
        workerDispatcher = Dispatchers.Unconfined
    )

    @Before
    fun setUp() {
        viewModel = spyk( FavoriteGameViewModel(getFavoriteGamesUsecase), recordPrivateCalls = true)
    }

    @Test
    fun when_get_favorite_games_then_get_list() = runTest {
        coEvery {
            getFavoriteGamesUsecase.invoke()
        } returns flow {
            emit(
                PagingData.from(
                    listOf(
                        Game(
                            slug = "suas",
                            name = "Susanna Farley",
                            genres = listOf(),
                            released = "cam",
                            backgroundImage = "nam",
                            description = "commune",
                            developer = ""
                        ),
                        Game(
                            slug = "doctus",
                            name = "Darren Donaldson",
                            genres = listOf(),
                            released = "movet",
                            backgroundImage = "movet",
                            description = "animal",
                            developer = ""
                        )
                    )
                )
            )
        }

        viewModel.onEvent(FavoriteGameViewModel.FavoriteGameEvent.GetFavoriteGames)
        verify {
            viewModel["getFavoriteGames"]()
        }
        viewModel.gamesState.test {
            differ.submitData(awaitItem())
            differ.snapshot().apply {
                println(this.items)
                Truth.assertThat(this.items.size).isEqualTo(2)
            }
        }

    }
    @Test
    fun when_get_favorite_games_then_get_empty() = runTest {
        coEvery {
            getFavoriteGamesUsecase.invoke()
        } returns flowOf(PagingData.empty())

        viewModel.onEvent(FavoriteGameViewModel.FavoriteGameEvent.GetFavoriteGames)
        verify {
            viewModel["getFavoriteGames"]()
        }
        viewModel.gamesState.test {
            differ.submitData(awaitItem())
            differ.snapshot().apply {
                println(this.items)
                Truth.assertThat(this.items.size).isEqualTo(0)
            }
        }

    }

}