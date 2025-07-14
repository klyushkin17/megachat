package com.example.chat_impl.utils

import com.example.chat_impl.data.remote.models.MessageDto
import com.example.chat_impl.domain.model.Message
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.serialization.json.Json

fun Frame.toMessage(): Message {
    val json = (this as? Frame.Text)?.readText() ?: ""
    val messageDto = Json.decodeFromString<MessageDto>(json)
    return messageDto.toMessage()
}