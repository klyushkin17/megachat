package com.example.chat_impl.presentation.model

import com.example.chat_impl.domain.model.Message

data class MessageUi(
    val id: String,
    val userId: String,
    val username: String,
    val time: String,
    val text: String,
    val isOwn: Boolean = false,
) {
    fun toMessage() =
        Message(
            id = id,
            userId = userId,
            username = username,
            formattedTime = time,
            text = text,
        )
}

