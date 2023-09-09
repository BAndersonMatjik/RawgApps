package com.dev.rawgapps.di

import com.dev.rawgapps.data.di.KtorClientModule
import com.dev.rawgapps.utils.RawgApiMock
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [KtorClientModule::class]
)
object FakeKtorModule {
    @Singleton
    @Provides
    fun provideKtorClient():HttpClient {
       return HttpClient(engine= RawgApiMock().engine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}


