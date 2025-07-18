package com.example.chat_impl.presentation.chat

sealed interface ChatActions {
    data class OnMessageSend(val message: String): ChatActions
    data class OnMessageChange(val message: String): ChatActions
    object OnLeaveChat: ChatActions
}