package com.dev.rawgapps.feature.game

import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailGameViewModelTest {
    private lateinit var viewModel: DetailGameViewModel
    private val getDetailGameUseCase: GetDetailGameUsecase = mockk()
    private val addFavoriteGameUsecase: AddFavoriteGameUsecase = mockk()

    @get:Rule
    private val mainDispatcherRule = MainDispatcherRule()
    private val fakeGame = Game(
        slug = "test",
        name = "Test Game",
        genres = listOf("Horro","RPG"),
        released = "Sep 2023, 12",
        backgroundImage = "http://image.com",
        description = "this is test game",
        developer = "BAM",
        isFavorite = false
    )
    @Before
    fun setup() {
        viewModel = spyk(DetailGameViewModel(getDetailGameUseCase,addFavoriteGameUsecase), recordPrivateCalls = true)
    }

    @Test
    fun given_slug_then_return_success_detail_data(){
        coEvery {
            getDetailGameUseCase("test")
        } returns flow {
            emit(Result.success(fakeGame))
        }
        runTest {
            viewModel.onEvent(event = DetailGameViewModel.DetailGameEvent.Init(game = fakeGame))
//            viewModel.uiState.up{
//
//            }
        }
    }
}