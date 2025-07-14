package com.example.chat_impl.domain.repository

import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import com.example.core.network.RootError

interface ChatServiceRepository {

    suspend fun getAllMessages(): Result<List<MessageUi>, ChatUiErrors>
}