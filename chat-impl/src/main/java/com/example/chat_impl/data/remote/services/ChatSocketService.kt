package com.example.chat_impl.data.remote.services

import com.example.chat_impl.domain.Message
import com.example.core.network.Result
import com.example.core.network.RootError
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initConnection(authToken: String): Boolean

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun cancelConnection()
}