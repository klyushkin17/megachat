package com.example.chat_api

import com.example.core.factory.ViewModelFactoryFactory

interface ChatApi {

    fun provideChatViewModelFactoryFactory(): ViewModelFactoryFactory
}