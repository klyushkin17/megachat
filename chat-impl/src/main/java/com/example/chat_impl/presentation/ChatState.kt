package com.example.chat_impl.presentation

import com.example.chat_impl.presentation.model.MessageUi

data class ChatState (
    val isLoading: Boolean = false,
    val error: ChatError =  ChatError.None,
    val messagesList: List<MessageUi> = emptyList(),
)

sealed interface ChatError {
    data object NoInternetConnection: ChatError
    data object SendMessageError: ChatError
    data object UnknownError: ChatError
    data object None: ChatError
}