package com.dev.rawgapps.data.remote.datasource

import androidx.core.text.HtmlCompat
import com.dev.rawgapps.utils.RawgApiMock
import com.google.common.truth.Truth
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.mockk.coEvery
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

class RawgRemoteDataSourceImplTest {
    private val mockEngine = RawgApiMock().engine

    private val httpClient = HttpClient(engine = mockEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    private val rawgRemoteDataSourceImpl = RawgRemoteDataSourceImpl(httpClient)

    @Test
    fun given_params_then_success_call_api() {
        runTest {
            rawgRemoteDataSourceImpl.getGames(1, 20).fold(onSuccess = {
                println("Size: ${it.size}")
                println("Result: ${it}")
                assert(it.isNotEmpty())
            }, onFailure = {

            })
        }
    }


    @Test
    fun when_getDetailGame_then_return_game() {
        mockkStatic(HtmlCompat::class)
        coEvery {
            HtmlCompat.fromHtml(any(),any()).toString()
        }returns "Testable"
        runTest {
            rawgRemoteDataSourceImpl.getGameDetail("forza-motorsport-2020").getOrThrow().apply {
                println(this)
                Truth.assertThat(name).isEqualTo("Forza Motorsport")
            }
        }
        unmockkAll()
    }
}