package com.example.chat_impl.data.remote.services

import com.example.chat_impl.domain.Message
import com.example.core.network.BaseUrl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import com.example.chat_impl.utils.toMessage
import io.ktor.websocket.close
import kotlinx.coroutines.flow.emptyFlow

class ChatSocketServiceImpl(
    val httpClient: HttpClient
): ChatSocketService {

    private var socketSession: WebSocketSession? = null

    override suspend fun initConnection(
        authToken: String
    ): Boolean{
        socketSession = httpClient.webSocketSession {
            url("${BaseUrl.CHAT_SOCKET_BASE_URL.baseUrl}?authToken=$authToken")
        }

        return socketSession?.isActive ?: false
    }

    override suspend fun sendMessage(message: String) {
        socketSession?.send(Frame.Text(message))
    }

    override fun observeMessages(): Flow<Message> =
        socketSession?.incoming
            ?.receiveAsFlow()
            ?.filter { it is Frame.Text }
            ?.map { it.toMessage() } ?: emptyFlow()

    override suspend fun cancelConnection() { socketSession?.close() }
}

