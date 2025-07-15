package com.example.chat_impl.di

import io.ktor.client.HttpClient
import retrofit2.Retrofit

interface ChatDepsProvider {

    fun getRetrofit(): Retrofit

    fun getWebSocketHttpClient(): HttpClient
}