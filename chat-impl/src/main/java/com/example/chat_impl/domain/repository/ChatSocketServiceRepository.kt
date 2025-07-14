package com.example.chat_impl.domain.repository

import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import com.example.core.network.RootError
import kotlinx.coroutines.flow.Flow

interface ChatSocketServiceRepository {

    suspend fun startConnection(
        authToken: String
    ): Flow<Result<MessageUi, RootError>>

    suspend fun stopConnection(): Result<Unit, RootError>

    suspend fun sendMessage(
        message: String
    ): Result<Unit, RootError>
}