package com.dev.rawgapps.domain.usecase

import app.cash.turbine.test
import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetDetailUsecaseImplTest {

    private lateinit var getDetailUsecaseImpl: GetDetailUsecaseImpl
    val rawgRepository = mockk<RawgRepository>()

    @Before
    fun setUp() {
        getDetailUsecaseImpl = GetDetailUsecaseImpl(rawgRepository = rawgRepository)
    }

    @Test
    fun when_get_detail_game_then_return_remote_data_only() {
        coEvery {
            rawgRepository.getGameFavorite("forza-motorsport")
        } returns flow {
            emit(Result.failure(Exception("Not Found")))
        }

        coEvery {
            rawgRepository.getGame("forza-motorsport")
        } returns flow {
            emit(
                Result.success(
                    Game(
                        slug = "Forza Motorsport",
                        name = "Forza Motorsport",
                        genres = listOf("Racing"),
                        released = "2023-10-10",
                        backgroundImage = "https://media.rawg.io/media/games/e9e/e9ecc560712ebfa6761e45d2523b0fb9.jpg",
                        description = "we love racing ",
                        developer = "Playground Games",
                        isFavorite = false
                    )
                )
            )
        }

        runTest {
            getDetailUsecaseImpl("forza-motorsport").test {
                awaitItem().getOrThrow().apply {
                    println(this)
                    Truth.assertThat(this.isFavorite).isFalse()
                    Truth.assertThat(name).isEqualTo("Forza Motorsport")
                }
                awaitComplete()
            }
        }
    }


    @Test
    fun when_get_detail_game_then_return_local_data_and_remote_race_condition() {
        coEvery {
            rawgRepository.getGameFavorite("forza-motorsport")
        } returns flow {
            emit(
                Result.success(
                    Game(
                        slug = "Forza Motorsport",
                        name = "Forza Motorsport",
                        genres = listOf("Racing"),
                        released = "2023-10-10",
                        backgroundImage = "https://media.rawg.io/media/games/e9e/e9ecc560712ebfa6761e45d2523b0fb9.jpg",
                        description = "we love racing ",
                        developer = "Playground Games",
                        isFavorite = true
                    )
                )
            )
        }

        coEvery {
            rawgRepository.getGame("forza-motorsport")
        } returns flow {
            emit(
                Result.success(
                    Game(
                        slug = "Forza Motorsport",
                        name = "Forza Motorsport",
                        genres = listOf("Racing"),
                        released = "2023-10-10",
                        backgroundImage = "https://media.rawg.io/media/games/e9e/e9ecc560712ebfa6761e45d2523b0fb9.jpg",
                        description = "we love racing ",
                        developer = "Playground Games",
                        isFavorite = false
                    )
                )
            )
        }

        runTest {
            getDetailUsecaseImpl("forza-motorsport").test {
                awaitItem().getOrThrow().apply {
                    println(this)
                    Truth.assertThat(this.isFavorite).isTrue()
                    Truth.assertThat(name).isEqualTo("Forza Motorsport")
                }
                awaitComplete()
            }
        }
    }


    @Test
    fun when_get_detail_game_then_return_local_data() {
        coEvery {
            rawgRepository.getGameFavorite("forza-motorsport")
        } returns flow {
            emit(
                Result.success(
                    Game(
                        slug = "Forza Motorsport",
                        name = "Forza Motorsport",
                        genres = listOf("Racing"),
                        released = "2023-10-10",
                        backgroundImage = "https://media.rawg.io/media/games/e9e/e9ecc560712ebfa6761e45d2523b0fb9.jpg",
                        description = "we love racing ",
                        developer = "Playground Games",
                        isFavorite = true
                    )
                )
            )
        }

        coEvery {
            rawgRepository.getGame("forza-motorsport")
        } returns flow {
            emit(
               Result.failure(Exception("error"))
            )
        }

        runTest {
            getDetailUsecaseImpl("forza-motorsport").test {
                awaitItem().getOrThrow().apply {
                    println(this)
                    Truth.assertThat(this.isFavorite).isTrue()
                    Truth.assertThat(name).isEqualTo("Forza Motorsport")
                }
                awaitComplete()
            }
        }
    }
}