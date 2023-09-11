package com.dev.rawgapps.feature.game

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.Turbine
import app.cash.turbine.turbineScope
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.GetGamesUsecase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds


class GameViewModelTest {
    private val getGamesUsecase = mockk<GetGamesUsecase>(relaxed = true)
    private lateinit var viewModel: GameViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = spyk(GameViewModel(getGamesUsecase), recordPrivateCalls = true)
    }

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
                                slug = "suas",
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
        }
    }

    @Test
    fun When_StateSearch_Expect_Error() {
        runTest {

//            emit(PagingData.empty(LoadStates(refresh = LoadState.Error(Exception("")),
//                prepend =LoadState.NotLoading(false),
//                append = LoadState.NotLoading(false)
//            )))
            coEvery {
                getGamesUsecase("susas")
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
                            )
                        )
                    )
                )
            }
            turbineScope {
                val standalone = Turbine<PagingData<Game>>(timeout = 10.milliseconds)
                val flow = viewModel.gamesState.asStateFlow().testIn(
                    scope = backgroundScope,
                    timeout = 10.milliseconds,
                )

                flow.awaitItem().map {
                    println(it)
                }
                flow.awaitComplete()
            }

            viewModel.onEvent(event = GameViewModel.GameEvent.SearchGames("susas"))
            verify {
                viewModel["getGamesAllOrSearch"]("susas")
            }
            coVerify { getGamesUsecase("susas") }


        }
    }

}


