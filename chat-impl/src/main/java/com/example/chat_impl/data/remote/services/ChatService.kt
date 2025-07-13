package com.example.chat_impl.data.remote.services

import com.example.chat_impl.data.remote.models.MessageDto
import retrofit2.http.GET

interface ChatService {

    @GET("/endPoint")
    suspend fun getAllMessages(): List<MessageDto>
}