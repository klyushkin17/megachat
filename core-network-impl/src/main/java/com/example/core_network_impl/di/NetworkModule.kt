package com.example.core_network_impl.di

import android.util.Log
import com.example.core.network.BaseUrl
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
object NetworkModule {

    @Provides
    @NetworkScope
    fun provideChatRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BaseUrl.CHAT_BASE_URL.baseUrl)
            .build()


    @Provides
    @NetworkScope
    fun provideWebSocketHttpClient(): HttpClient =
        HttpClient(CIO) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KTOR", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }
}