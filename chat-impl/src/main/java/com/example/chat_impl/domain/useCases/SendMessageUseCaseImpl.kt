package com.example.chat_impl.domain.useCases

import com.example.chat_impl.domain.repository.ChatSocketServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result

class SendMessageUseCaseImpl(
    private val chatSocketServiceRepository: ChatSocketServiceRepository
): SendMessageUseCase {

    override suspend fun invoke(message: String): Result<Unit, ChatUiErrors> {
        return chatSocketServiceRepository.sendMessage(message)
    }
}