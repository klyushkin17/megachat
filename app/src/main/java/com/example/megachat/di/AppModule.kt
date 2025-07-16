package com.example.megachat.di

import com.example.core_network_impl.di.NetworkComponent
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import retrofit2.Retrofit

@Module(includes = [
    NetworkDepsModule::class,
])
interface AppModule

@Module
object NetworkDepsModule {

    @AppScope
    @Provides
    fun provideRetrofit(): Retrofit = NetworkComponent.init().provideRetrofit()

    @AppScope
    @Provides
    fun provideWebSocketHttpClient(): HttpClient = NetworkComponent.init().provideWebSocketHttpClient()
}