package com.example.chat_impl.domain.useCases

import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import kotlinx.coroutines.flow.Flow

interface StartConnectionUseCase {

    suspend operator fun invoke(authToken: String): Flow<Result<MessageUi, ChatUiErrors>>
}