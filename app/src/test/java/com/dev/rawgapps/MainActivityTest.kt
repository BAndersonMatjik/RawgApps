package com.dev.rawgapps

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.rememberNavController
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, manifest = Config.NONE)
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    @Test
    fun when_I_test_then_it_works2() {
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithText("Rawg games".uppercase(), useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Search Games", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `retrieve list of games be successfull`() {
        composeTestRule.onRoot().printToLog("TAG")
        //Given the user opens the application
        composeTestRule.activity.setContent {
            RawgAppsTheme {
                MainScreen(navController = rememberNavController())
            }
        }

        composeTestRule.onNodeWithText("Grand Theft Auto V").assertIsDisplayed()

    }


}