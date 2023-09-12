package com.dev.rawgapps.data.repository

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import app.cash.turbine.test
import com.dev.rawgapps.common.Constants
import com.dev.rawgapps.data.local.datasource.RawgLocalDataSource
import com.dev.rawgapps.data.remote.datasource.GamePagingDataSource
import com.dev.rawgapps.data.remote.datasource.PageDataSourceFacade
import com.dev.rawgapps.data.remote.datasource.RawgRemoteDataSource
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.feature.game.MainDispatcherRule
import com.dev.rawgapps.feature.game.TestDiffCallback
import com.dev.rawgapps.feature.game.TestListCallback
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RawgRepositoryImplTest {
    @get:Rule(order = 0)
    val mainDispatcherRule = MainDispatcherRule()
    private val gamePageDataSourceFacade: PageDataSourceFacade<GamePagingDataSource> = mockk()
    private val gameRemoteDataSource: RawgRemoteDataSource = mockk()
    private val gameLocalDataSource: RawgLocalDataSource = mockk()
    private val differ = AsyncPagingDataDiffer(
        diffCallback = TestDiffCallback<Game>(),
        updateCallback = TestListCallback(),
        workerDispatcher = Dispatchers.Unconfined
    )
    private val fakeGame = Game(
        slug = "Assassins-Mirage",
        name = "Assassins Mirage",
        genres = listOf("MMORPG"),
        released = "Des 2023,12",
        backgroundImage = "http:test.com",
        description = "Nostalgia beneran ini mah, bener2 balik jaman dulu | Preview Assasin’s Creed Mirage",
        developer = "Ubisoft",
        isFavorite = false
    )
    private lateinit var rawgRepositoryImpl: RawgRepositoryImpl

    @Before
    fun setUp() {
        rawgRepositoryImpl =
            RawgRepositoryImpl(gamePageDataSourceFacade, gameRemoteDataSource, gameLocalDataSource)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun when_get_favorite_games_then_return_paging_data() {
        runTest {
            coEvery {
                gameLocalDataSource.getFavoriteGames()
            } returns flow {
                emit(PagingData.empty<Game>())
            }
            rawgRepositoryImpl.getFavoriteGames().test {
                differ.submitData(awaitItem())
                differ.snapshot().apply {
                    Truth.assertThat(this.items.size).isEqualTo(0)
                }
                awaitComplete()
            }
        }
    }

    @Test
    fun when_get_favorite_game_then_return_flow_game() {
        runTest {
            coEvery {
                gameLocalDataSource.getGameBySlag("Assassins-Mirage")
            } returns flow {
                emit(Result.success(fakeGame))
            }
            rawgRepositoryImpl.getGameFavorite("Assassins-Mirage").test {
                awaitItem().apply {
                    Truth.assertThat(isSuccess).isTrue()
                    Truth.assertThat(getOrNull()).isNotNull()
                    this.getOrNull().apply {
                        Truth.assertThat(this?.name).isEqualTo("Assassins Mirage")
                    }
                }
                awaitComplete()
            }
        }
    }


    @Test
    fun when_remove_favorite_game() {
        coEvery { gameLocalDataSource.deleteFavoriteGame("RAWG-1") } returns Unit
        runTest {
            rawgRepositoryImpl.removeFavoriteGame("RAWG-1")
        }
    }

    @Test
    fun when_add_favorite_game() {

        coEvery { gameLocalDataSource.addFavoriteGame(fakeGame) } returns Unit
        runTest {
            rawgRepositoryImpl.addFavoriteGame(fakeGame)
        }
    }

    @Test
    fun when_get_game_then_return_flow_game() {
        runTest {
            coEvery {
                gameRemoteDataSource.getGameDetail("Assassins-Mirage")
            } returns Result.success(fakeGame)
            rawgRepositoryImpl.getGame("Assassins-Mirage").test {
                awaitItem().apply {
                    Truth.assertThat(isSuccess)
                    Truth.assertThat(getOrNull()).isNotNull()
                    this.getOrNull().apply {
                        Truth.assertThat(this?.name).isEqualTo("Assassins Mirage")
                    }
                }
                awaitComplete()
            }
        }
    }

    fun when_get_games_then_return_flow_game() {
        runTest {
            coEvery {
                gameRemoteDataSource.getGames(
                    1,
                    Constants.PAGE_SIZE,
                    "Assassins-Mirage"
                )
            } returns Result.success(
                listOf(
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                    fakeGame,
                )
            )
            coEvery {
                gameRemoteDataSource.getGames(
                    2,
                    Constants.PAGE_SIZE,
                    "Assassins-Mirage"
                )
            } returns Result.success(listOf())

            coEvery {
                gamePageDataSourceFacade.create("Assassins-Mirage")
            } returns GamePagingDataSource(
                rawgRemoteDataSource = gameRemoteDataSource,
                query = "Assassins-Mirage"
            )

            rawgRepositoryImpl.getGames("Assassins-Mirage").test {
                differ.submitData(awaitItem())
                differ.snapshot().apply {
                    Truth.assertThat(items.size).isEqualTo(1)
                }
                awaitComplete()
            }
        }
    }

}