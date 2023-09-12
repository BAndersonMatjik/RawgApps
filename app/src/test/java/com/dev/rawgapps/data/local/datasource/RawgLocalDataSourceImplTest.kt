package com.dev.rawgapps.data.local.datasource

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.dev.rawgapps.data.local.config.RawgDatabase
import com.dev.rawgapps.utils.FakeData
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class RawgLocalDataSourceImplTest {
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var context: Context
    private lateinit var database: RawgDatabase
    private lateinit var rawgLocalDataSource: RawgLocalDataSource

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, RawgDatabase::class.java).allowMainThreadQueries()
                .build()
        rawgLocalDataSource = RawgLocalDataSourceImpl(dao = database.favoriteDao())
    }

    @After
    fun cleanUp(){
        context.deleteDatabase(RawgDatabase.DB_NAME)
    }

    @Test
    fun given_one_favorite_when_delete_then_favorite_game_removed(){
        val fakeGame = FakeData.getGame()
        runTest {
            rawgLocalDataSource.addFavoriteGame(fakeGame)
            rawgLocalDataSource.getGameBySlag(fakeGame.slug).test {
                awaitItem().getOrNull().apply {
                    Truth.assertThat(this).isNotNull()
                }
                rawgLocalDataSource.deleteFavoriteGame(fakeGame.slug)
                awaitItem().getOrNull().apply {
                    Truth.assertThat(this).isNull()
                }
            }

        }
    }

    @Test
    fun when_add_favorite_game_then_save_to_local_db(){
        val fakeGame = FakeData.getGame()
        runTest {
            rawgLocalDataSource.addFavoriteGame(fakeGame)
            rawgLocalDataSource.getGameBySlag(fakeGame.slug).test {
                awaitItem().getOrNull().apply {
                    Truth.assertThat(this).isNotNull()
                    println(this)
                }
            }

        }
    }

    @Test
    fun when_get_favorite_game_then_return_as_success(){
        val fakeGame = FakeData.getGame()
        runTest {
            rawgLocalDataSource.addFavoriteGame(fakeGame)
            rawgLocalDataSource.getGameBySlag(fakeGame.slug).test {
                awaitItem().getOrNull().apply {
                    Truth.assertThat(this).isNotNull()
                    println(this)
                }
            }
        }
    }

}