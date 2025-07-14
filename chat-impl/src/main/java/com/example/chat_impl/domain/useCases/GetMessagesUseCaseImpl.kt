package com.example.chat_impl.domain.useCases

import com.example.chat_impl.domain.repository.ChatServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result

class GetMessagesUseCaseImpl(
    private val chatServiceRepository: ChatServiceRepository
): GetMessagesUseCase {

    override suspend fun invoke(): Result<List<MessageUi>, ChatUiErrors> {
        return chatServiceRepository.getAllMessages()
    }
}