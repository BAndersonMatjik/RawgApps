package com.dev.rawgapps.feature.favoritegame

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import com.dev.rawgapps.utils.FakeData
import kotlinx.coroutines.flow.flow
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

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
@LooperMode(LooperMode.Mode.PAUSED)
class FavoriteGameScreensTest {

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
    fun `retrieve list of games be successfull`() {

        val gamePagingData = flow {
            emit(PagingData.from(listOf(FakeData.getGame())))
        }
        //Given the user opens the application
        composeTestRule.setContent {
            RawgAppsTheme {
                FavoriteGameScreen(gamePagingItems = gamePagingData.collectAsLazyPagingItems())
            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithText("Forza Motorsport").assertIsDisplayed()
    }

}