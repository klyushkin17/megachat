package com.example.chat_impl.data.repository

import com.example.chat_impl.data.dataSources.ChatSocketServiceDataSource
import com.example.chat_impl.domain.model.Message
import com.example.chat_impl.domain.repository.ChatSocketServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.errors.DataError
import com.example.core.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ChatSocketServiceRepositoryImpl(
    private val chatSocketServiceDataSource: ChatSocketServiceDataSource,
): ChatSocketServiceRepository {

    override suspend fun startConnection(
        authToken: String
    ): Flow<Result<MessageUi, ChatUiErrors>> {
        val connectionResult = chatSocketServiceDataSource.initConnection(authToken)

        return when(connectionResult) {
            is Result.Success -> {
                chatSocketServiceDataSource.observeMessages()
                    .map { messageResult ->
                        when(messageResult) {
                            is Result.Success -> {
                                val messageUi = messageResult.data.toMessageUi()
                                Result.Success(messageUi)
                            }
                            is Result.Error -> {
                                when(messageResult.error) {
                                    DataError.Network.NO_INTERNET -> {
                                        Result.Error(ChatUiErrors.NO_INTERNET_CONNECTION)
                                    }
                                    else -> {Result.Error(ChatUiErrors.OTHER_ERROR)}
                                }
                            }
                        }
                    }
            }
            is Result.Error -> {
                when(connectionResult.error) {
                    DataError.Network.NO_INTERNET -> {
                        flowOf(Result.Error(ChatUiErrors.NO_INTERNET_CONNECTION))
                    }
                    else -> { flowOf(Result.Error(ChatUiErrors.OTHER_ERROR)) }
                }
            }
        }
    }

    override suspend fun stopConnection(): Result<Unit, ChatUiErrors> {
        val stopResult = chatSocketServiceDataSource.cancelConnection()

        return when(stopResult) {
            is Result.Success -> { Result.Success(Unit) }
            is Result.Error -> {
                when(stopResult.error) {
                    DataError.Network.NO_INTERNET -> {
                        Result.Error(ChatUiErrors.NO_INTERNET_CONNECTION)
                    }
                    else -> {Result.Error(ChatUiErrors.OTHER_ERROR)}
                }
            }
        }
    }

    override suspend fun sendMessage(message: String): Result<Unit, ChatUiErrors> {
        val sendMessageResult = chatSocketServiceDataSource.sendMessage(message)

        return when(sendMessageResult) {
            is Result.Success -> { Result.Success(Unit) }
            is Result.Error -> {
                when(sendMessageResult.error) {
                    DataError.Network.NO_INTERNET,
                    DataError.Network.WEBSOCKET_CONNECTION_FAILED -> {
                        Result.Error(ChatUiErrors.SEND_MESSAGE_ERROR)
                    }
                    else -> {Result.Error(ChatUiErrors.OTHER_ERROR)}
                }
            }
        }
    }
}