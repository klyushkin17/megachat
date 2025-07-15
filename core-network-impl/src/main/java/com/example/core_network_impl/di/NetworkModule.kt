package com.example.core_network_impl.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
object NetworkModule {

    @Provides
    @NetworkScope
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @NetworkScope
    fun provideWebSocketHttpClient(): HttpClient =
        HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }
}