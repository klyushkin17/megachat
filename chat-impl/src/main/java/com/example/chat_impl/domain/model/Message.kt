package com.example.chat_impl.domain.model

import com.example.chat_impl.presentation.model.MessageUi

data class Message(
    val text: String
) {
    fun toMessageUi(): MessageUi =
        MessageUi(
            text = text
        )
}