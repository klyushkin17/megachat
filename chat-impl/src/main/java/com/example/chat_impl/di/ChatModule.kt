package com.example.chat_impl.di

import com.example.chat_impl.data.dataSources.ChatServiceDataSource
import com.example.chat_impl.data.dataSources.ChatServiceDataSourceImpl
import com.example.chat_impl.data.dataSources.ChatSocketServiceDataSource
import com.example.chat_impl.data.dataSources.ChatSocketServiceDataSourceImpl
import com.example.chat_impl.data.remote.services.ChatService
import com.example.chat_impl.data.remote.services.ChatSocketService
import com.example.chat_impl.data.remote.services.ChatSocketServiceImpl
import com.example.chat_impl.data.repository.ChatServiceRepositoryImpl
import com.example.chat_impl.data.repository.ChatSocketServiceRepositoryImpl
import com.example.chat_impl.domain.repository.ChatServiceRepository
import com.example.chat_impl.domain.repository.ChatSocketServiceRepository
import com.example.chat_impl.domain.useCases.GetMessagesUseCase
import com.example.chat_impl.domain.useCases.GetMessagesUseCaseImpl
import com.example.chat_impl.domain.useCases.SendMessageUseCase
import com.example.chat_impl.domain.useCases.SendMessageUseCaseImpl
import com.example.chat_impl.domain.useCases.StartConnectionUseCase
import com.example.chat_impl.domain.useCases.StartConnectionUseCaseImpl
import com.example.chat_impl.domain.useCases.StopConnectionUseCase
import com.example.chat_impl.domain.useCases.StopConnectionUseCaseImpl
import com.example.core.network.BaseUrl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [
    RetrofitModule::class,
    BindingModule::class,
    ViewModelModule::class,
])
interface ChatModule

@Module
object RetrofitModule {

    @ChatScope
    @Provides
    fun provideChatService(
        chatServiceRetrofit: Retrofit
    ): ChatService {
        return chatServiceRetrofit
            .create(ChatService::class.java)
    }
}

@Module
object ViewModelModule {

}

@Module
interface BindingModule {

    @Binds
    fun bindsChatSocketService(
        chatSocketServiceImpl: ChatSocketServiceImpl
    ): ChatSocketService

    @Binds
    fun bindsChatServiceDataSource(
        chatServiceDataSourceImpl: ChatServiceDataSourceImpl
    ): ChatServiceDataSource

    @Binds
    fun bindsChatSocketServiceDataSource(
        chatSocketServiceDataSourceImpl: ChatSocketServiceDataSourceImpl
    ): ChatSocketServiceDataSource

    @Binds
    fun bindsChatServiceRepository(
        chatServiceRepositoryImpl: ChatServiceRepositoryImpl
    ): ChatServiceRepository

    @Binds
    fun bindsChatSocketServiceRepository(
        chatSocketServiceRepositoryImpl: ChatSocketServiceRepositoryImpl
    ): ChatSocketServiceRepository

    @Binds
    fun bindsGetMessagesUseCase(
        getMessageUseCaseImpl: GetMessagesUseCaseImpl
    ): GetMessagesUseCase

    @Binds
    fun bindsSendMessageUseCase(
        sendMessageUseCaseImpl: SendMessageUseCaseImpl
    ): SendMessageUseCase

    @Binds
    fun bindStartConnectionUseCase(
        startConnectionUseCaseImpl: StartConnectionUseCaseImpl
    ): StartConnectionUseCase

    @Binds
    fun bindStopConnectionUseCase(
        stopConnectionUseCaseImpl: StopConnectionUseCaseImpl
    ): StopConnectionUseCase
}
