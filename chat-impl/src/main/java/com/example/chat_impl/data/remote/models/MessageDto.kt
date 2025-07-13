package com.example.chat_impl.data.remote.models

import com.squareup.moshi.Json

data class MessageDto(
    @field:Json("text") val text: String
)
