package com.dev.rawgapps.feature.game

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.shadows.ShadowLog
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
@LooperMode(LooperMode.Mode.PAUSED)
class DetailGameScreen() {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Before
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    @After
    fun tearDown() {
        ShadowLog.stream = null
    }

    @Test
    fun when_data_still_not_complete_then_hide_favorite_icon(){
        val state = DetailGameViewModel.DetailGameViewState(showFavoriteIcon = false)
        composeTestRule.setContent {
            DetailGameScreen(uiState = state)
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("favoriteIconGroup").assertDoesNotExist()

    }
    @Test
    fun when_data_complete_then_show_favorite_icon(){
        val state = DetailGameViewModel.DetailGameViewState(showFavoriteIcon = true)
        composeTestRule.setContent {
            DetailGameScreen(uiState = state)
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("favoriteIconGroup").assertIsDisplayed()
    }


    @Test
    fun when_error_then_show_toast_message(){
        val state = DetailGameViewModel.DetailGameViewState(errorFetchDetailGame = "Failed Fetch Detail Game")
        composeTestRule.setContent {
            DetailGameScreen(uiState = state)
        }
        composeTestRule.onRoot().printToLog("TAG")
        Truth.assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("Failed Fetch Detail Game")
    }


    @Test
    fun when_click_favorite_and_show_favorite_button_then_show_toast(){
        val state = DetailGameViewModel.DetailGameViewState(messageToast = "Add to favorite", showFavoriteIcon = true)
        composeTestRule.setContent {
            DetailGameScreen(uiState = state)
        }
        composeTestRule.onRoot().printToLog("TAG")
        Truth.assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("Add to favorite")
    }

    @Test
    fun when_click_favorite_and_hide_favorite_button_then_show_toast(){
        val state = DetailGameViewModel.DetailGameViewState(messageToast = "Add to favorite", showFavoriteIcon = false)
        composeTestRule.setContent {
            DetailGameScreen(uiState = state)
        }
        composeTestRule.onRoot().printToLog("TAG")
        Truth.assertThat(ShadowToast.getTextOfLatestToast()).isNull()
    }
}

