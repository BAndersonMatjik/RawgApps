package com.dev.rawgapps.domain.usecase

import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.domain.Game
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class AddFavoriteGameUsecaseImplTest {
    private lateinit var addFavoriteGamesUsecase: AddFavoriteGameUsecase
    private val rawgRepository = mockk<RawgRepository>()
    private val fakeGame = Game(
        slug = "Forza Motorsport",
        name = "Forza Motorsport",
        genres = listOf("Racing"),
        released = "2023-10-10",
        backgroundImage = "https://media.rawg.io/media/games/e9e/e9ecc560712ebfa6761e45d2523b0fb9.jpg",
        description = "we love racing ",
        developer = "Playground Games",
        isFavorite = false
    )
    @Before
    fun setUp() {
        addFavoriteGamesUsecase = AddFavoriteGameUsecaseImpl(rawgRepository = rawgRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun when_add_favorite_then_added_to_favorite(){
        coEvery {
            rawgRepository.addFavoriteGame(fakeGame)
        }returns Unit
        runTest {
            addFavoriteGamesUsecase(fakeGame).apply {
                Truth.assertThat(this).isTrue()
            }
        }
    }
    @Test
    fun when_add_favorite_then_removed_to_favorite(){
        val fake = fakeGame.copy(isFavorite = true)
        coEvery {
            rawgRepository.removeFavoriteGame(fake.slug)
        }returns Unit
        runTest {
            addFavoriteGamesUsecase(fake).apply {
                Truth.assertThat(this).isFalse()
            }
        }
    }
}