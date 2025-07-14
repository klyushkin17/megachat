package com.example.chat_impl.data.dataSources

import com.example.chat_impl.domain.Message
import com.example.core.errors.DataError
import com.example.core.network.Result
import kotlinx.coroutines.flow.Flow

interface ChatSocketServiceDataSource {

    suspend fun initConnection(authToken: String): Result<Unit, DataError.Network>

    suspend fun sendMessage(message: String): Result<Unit, DataError.Network>

    fun observeMessages(): Flow<Result<Message, DataError.Network>>

    suspend fun cancelConnection(): Result<Unit, DataError.Network>
}