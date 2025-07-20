package com.example.chat_impl.domain.model

import com.example.chat_impl.presentation.model.MessageUi

data class Message(
    val id: String,
    val userId: String,
    val username: String,
    val formattedTime: String,
    val text: String,
) {
    fun toMessageUi(): MessageUi =
        MessageUi(
            id = id,
            userId = userId,
            username = username,
            time = formattedTime,
            text = text,
        )
}