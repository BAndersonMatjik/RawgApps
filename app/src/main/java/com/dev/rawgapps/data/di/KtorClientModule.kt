package com.dev.rawgapps.data.di

import com.dev.rawgapps.data.remote.ApiRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorClientModule {
    @Provides
    @Singleton
    fun provideKtorClient():HttpClient{
        return HttpClient {
            install(JsonFeature){
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("Logger Ktor =>").v(message)
                    }
                }
                level = LogLevel.INFO
            }

            install(ResponseObserver){
                onResponse {
                    Timber.tag("HTTP status:").d(it.status.value.toString())
                }
            }

            install(DefaultRequest){
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                //set base url
                host = ApiRoutes.BASE_URL
                url.protocol = URLProtocol.HTTPS
                url.parameters.append("key","${ApiRoutes.API_KEY}")
            }
        }
    }
}