package com.example.chat_impl.data.repository

import com.example.chat_impl.data.dataSources.ChatServiceDataSource
import com.example.chat_impl.domain.repository.ChatServiceRepository
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import com.example.core.network.RootError

class ChatServiceRepositoryImpl(
    private val chatServiceDataSource: ChatServiceDataSource
): ChatServiceRepository {

    override suspend fun getAllMessages(): Result<List<MessageUi>, RootError> {

    }
}