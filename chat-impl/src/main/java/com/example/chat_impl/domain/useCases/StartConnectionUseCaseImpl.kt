package com.example.chat_impl.domain.useCases

import com.example.chat_impl.domain.repository.ChatSocketServiceRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartConnectionUseCaseImpl @Inject constructor(
    private val chatSocketServiceRepository: ChatSocketServiceRepository
): StartConnectionUseCase{

    override suspend fun invoke(authToken: String): Flow<Result<MessageUi, ChatUiErrors>> {
        return chatSocketServiceRepository.startConnection(authToken)
    }
}