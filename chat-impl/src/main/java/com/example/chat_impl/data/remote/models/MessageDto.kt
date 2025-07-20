package com.example.chat_impl.data.remote.models

import android.icu.text.DateFormat
import com.example.chat_impl.domain.model.Message
import com.squareup.moshi.Json
import java.util.Date

data class MessageDto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "userId") val userId: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "timestamp") val timestamp: Long,
    @field:Json(name = "text") val text: String
) {
    fun toMessage(): Message {
        val date = Date(timestamp)
        val formattedTime = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(date)

        return Message(
            id = id,
            userId = userId,
            username = username,
            formattedTime = formattedTime,
            text = text
        )
    }
}

