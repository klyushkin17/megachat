package com.example.chat_impl.presentation.model

import com.example.chat_impl.domain.model.Message

data class MessageUi(
    val text: String
) {
    fun toMessage() =
        Message(
            text = text
        )
}

