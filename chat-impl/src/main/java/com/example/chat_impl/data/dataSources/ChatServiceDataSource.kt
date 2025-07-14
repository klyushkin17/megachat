package com.example.chat_impl.data.dataSources

import com.example.chat_impl.domain.model.Message
import com.example.core.errors.DataError
import com.example.core.network.Result

interface ChatServiceDataSource {

    suspend fun getAllMessages(): Result<List<Message>, DataError.Network>
}