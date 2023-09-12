package com.dev.rawgapps.feature.game

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import app.cash.turbine.test
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.GetGamesUsecase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GameViewModelTest {
    private val getGamesUsecase = mockk<GetGamesUsecase>(relaxed = true)
    private lateinit var viewModel: GameViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = spyk(GameViewModel(getGamesUsecase), recordPrivateCalls = true)
    }
    private val differ = AsyncPagingDataDiffer(
        diffCallback = TestDiffCallback<Game>(),
        updateCallback = TestListCallback(),
        workerDispatcher = Dispatchers.Unconfined
    )
    // When_StateUnderTest_Expect_ExpectedBehavior
    @Test
    fun When_StateGetGames_Expect_ShowGames() {
        runTest {
            coEvery {
                getGamesUsecase("")
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
            viewModel.onEvent(event = GameViewModel.GameEvent.GetGames)
            verify {
                viewModel["getGamesAllOrSearch"]("")
            }
            coVerify { getGamesUsecase("") }

            viewModel.gamesState.test {
                differ.submitData(awaitItem())
                differ.snapshot().apply {
                    println(this.items)
                    Truth.assertThat(this.items.size).isEqualTo(2)
                    Truth.assertThat(this.items[0].name).isEqualTo("Susanna Farley")
                    Truth.assertThat(this.items[1].name).isEqualTo("Darren Donaldson")
                }
            }
        }
    }


    @Test
    fun When_StateSearch_Expect_ShowGames() {
        runTest {
            coEvery {
                getGamesUsecase("susas")
            } returns flow {
                emit(
                    PagingData.from(
                        listOf(
                            Game(
                                slug = "sus",
                                name = "Susanna Farley",
                                genres = listOf(),
                                released = "cam",
                                backgroundImage = "nam",
                                description = "commune",
                                developer = ""
                            )
                        )
                    )
                )
            }
            viewModel.onEvent(event = GameViewModel.GameEvent.SearchGames("susas"))
            verify {
                viewModel["getGamesAllOrSearch"]("susas")
            }
            coVerify { getGamesUsecase("susas") }
            viewModel.gamesState.test {
                differ.submitData(awaitItem())
                differ.snapshot().apply {
                    println(this.items)
                    Truth.assertThat(this.items.size).isEqualTo(1)
                    Truth.assertThat(this.items[0].name).isEqualTo("Susanna Farley")
                }
            }
        }

    }

}

