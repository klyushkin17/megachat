package com.example.core_network_api

import io.ktor.client.HttpClient
import retrofit2.Retrofit

interface NetworkApi {

    fun provideRetrofit(): Retrofit

    fun provideWebSocketHttpClient(): HttpClient
}