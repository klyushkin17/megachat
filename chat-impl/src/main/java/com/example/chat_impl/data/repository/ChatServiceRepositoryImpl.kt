package com.example.chat_impl.data.repository

import com.example.chat_impl.data.dataSources.ChatServiceDataSource
import com.example.chat_impl.domain.model.Message
import com.example.chat_impl.domain.repository.ChatServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.chat_impl.test.getAllMessagesTestSuccess
import com.example.core.errors.DataError
import com.example.core.network.Result
import com.example.core.network.RootError
import javax.inject.Inject

class ChatServiceRepositoryImpl @Inject constructor(
    private val chatServiceDataSource: ChatServiceDataSource
): ChatServiceRepository {

    override suspend fun getAllMessages(): Result<List<Message>, ChatUiErrors> {
        // val messagesResult = chatServiceDataSource.getAllMessages()
        val messagesResult = getAllMessagesTestSuccess()

        return when(messagesResult) {
            is Result.Success -> {
                val messagesUiList = messagesResult.data
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