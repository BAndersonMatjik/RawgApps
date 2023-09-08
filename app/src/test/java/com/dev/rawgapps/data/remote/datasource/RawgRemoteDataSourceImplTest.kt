package com.dev.rawgapps.data.remote.datasource

import com.dev.rawgapps.utils.RawgApiMock
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test

class RawgRemoteDataSourceImplTest {
    private val mockEngine = RawgApiMock().engine

    private val httpClient = HttpClient(engine= mockEngine) {
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
    fun given_params_then_success_call_api(){
        runTest {
            rawgRemoteDataSourceImpl.getGames(1,20).fold(onSuccess = {
                println("Size: ${it.size}")
                println("Result: ${it}")
                assert(it.isNotEmpty())
            }, onFailure = {

            })
        }
    }
}