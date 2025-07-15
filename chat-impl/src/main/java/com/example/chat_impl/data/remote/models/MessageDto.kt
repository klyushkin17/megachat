package com.example.chat_impl.data.remote.models

import com.example.chat_impl.domain.model.Message
import com.squareup.moshi.Json

data class MessageDto(
    @field:Json(name = "text") val text: String
) {
    fun toMessage(): Message =
        Message(
            text = text
        )
}

