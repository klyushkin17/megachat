package com.example.chat_impl.domain.useCases

import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.core.network.Result

interface StopConnectionUseCase {

    suspend operator fun invoke(): Result<Unit, ChatUiErrors>
}