package com.example.chat_impl.domain.useCases

import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result

interface GetMessagesUseCase {

    suspend operator fun invoke(): Result<List<MessageUi>, ChatUiErrors>
}