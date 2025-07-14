package com.example.chat_impl.data.repository

import com.example.chat_impl.data.dataSources.ChatServiceDataSource
import com.example.chat_impl.domain.repository.ChatServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.errors.DataError
import com.example.core.network.Result
import com.example.core.network.RootError

class ChatServiceRepositoryImpl(
    private val chatServiceDataSource: ChatServiceDataSource
): ChatServiceRepository {

    override suspend fun getAllMessages(): Result<List<MessageUi>, ChatUiErrors> {
        val messagesResult = chatServiceDataSource.getAllMessages()

        return when(messagesResult) {
            is Result.Success -> {
                val messagesUiList = messagesResult.data.map { it.toMessageUi() }
                Result.Success(messagesUiList)
            }
            is Result.Error -> {
                when(messagesResult.error) {
                    DataError.Network.NO_INTERNET -> Result.Error(ChatUiErrors.NO_INTERNET_CONNECTION)
                    else -> Result.Error(ChatUiErrors.OTHER_ERROR)
                }
            }
        }
    }
}