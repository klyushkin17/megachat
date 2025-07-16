package com.example.megachat.di

import androidx.lifecycle.ViewModel
import com.example.chat_impl.di.ChatComponent
import com.example.chat_impl.di.ChatDepsProvider
import com.example.chat_impl.presentation.ChatViewModel
import com.example.core.factory.ViewModelFactoryFactory
import com.example.core_network_impl.di.NetworkComponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import retrofit2.Retrofit

@Module(includes = [
    NetworkDepsModule::class,
    ViewModelModule::class,
    BindingViewModelModule::class,
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

@Module
object ViewModelModule {

    @AppScope
    @Provides
    fun provideChatViewModelFactoryFactory(
        chatDepsProvider: ChatDepsProvider
    ): ViewModelFactoryFactory =
        ChatComponent.init(chatDepsProvider).provideChatViewModelFactoryFactory()
}

@Module
interface BindingViewModelModule {

    @AppScope
    @Binds
    fun bindsChatViewModelFactoryFactory(
        chatViewModelFactoryFactory: ViewModelFactoryFactory
    ): ChatViewModel.ChatViewModelFactory.Factory
}
