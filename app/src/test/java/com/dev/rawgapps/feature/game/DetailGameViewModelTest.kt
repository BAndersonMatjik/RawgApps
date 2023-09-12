package com.dev.rawgapps.feature.game

import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class DetailGameViewModelTest {
    private lateinit var viewModel: DetailGameViewModel
    private val getDetailGameUseCase: GetDetailGameUsecase = mockk()
    private val addFavoriteGameUsecase: AddFavoriteGameUsecase = mockk()

    @get:Rule
    private val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = spyk(GameDe(getGamesUsecase), recordPrivateCalls = true)
    }
}