package com.example.chat_impl.presentation.model

import com.example.chat_impl.domain.model.Message

data class MessageUi(
    val id: String,
    val username: String,
    val time: String,
    val text: String
) {
    fun toMessage() =
        Message(
            id = id,
            username = username,
            formattedTime = time,
            text = text,
        )
}

