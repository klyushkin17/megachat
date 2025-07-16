package com.example.chat_impl.domain.useCases

import com.example.chat_impl.domain.repository.ChatSocketServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.core.network.Result
import javax.inject.Inject

class StopConnectionUseCaseImpl @Inject constructor(
    private val chatSocketServiceRepository: ChatSocketServiceRepository
): StopConnectionUseCase {
    override suspend fun invoke(): Result<Unit, ChatUiErrors> {
        return chatSocketServiceRepository.stopConnection()
    }
}