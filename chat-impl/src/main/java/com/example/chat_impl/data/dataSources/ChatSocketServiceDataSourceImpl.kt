package com.example.chat_impl.data.dataSources

import com.example.chat_impl.data.remote.services.ChatSocketService
import com.example.chat_impl.domain.Message
import com.example.core.errors.DataError
import com.example.core.network.Result
import io.ktor.client.plugins.websocket.WebSocketException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerializationException
import okio.IOException
import java.util.concurrent.TimeoutException

class ChatSocketServiceDataSourceImpl(
    private val chatSocketService: ChatSocketService
): ChatSocketServiceDataSource {
    override suspend fun initConnection(authToken: String): Result<Unit, DataError.Network> =
        try {
            val isConnected = chatSocketService.initConnection("DUMMY_TOKEN")
            if (isConnected) Result.Success(Unit)
            else Result.Error(DataError.Network.WEBSOCKET_CONNECTION_FAILED)
        } catch (e: IOException) {
            Result.Error(DataError.Network.NO_INTERNET)
        } catch (e: TimeoutException) {
            Result.Error(DataError.Network.REQUEST_TIMEOUT)
        } catch (e: WebSocketException) {
            when {
                e.message?.contains("handshake") == true -> {
                    Result.Error(DataError.Network.WEBSOCKET_HANDSHAKE_FAILED)
                }
                else -> Result.Error(DataError.Network.SERVER_ERROR)
            }
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }

    override suspend fun sendMessage(message: String): Result<Unit, DataError.Network> =
        try {
            chatSocketService.sendMessage(message)
            Result.Success(Unit)
        } catch (e: IOException) {
            Result.Error(DataError.Network.NO_INTERNET)
        } catch (e: TimeoutException) {
            Result.Error(DataError.Network.REQUEST_TIMEOUT)
        } catch (e: WebSocketException) {
            when {
                e.message?.contains("closed") == true -> {
                    Result.Error(DataError.Network.WEBSOCKET_SESSION_CLOSED)
                }
                else -> Result.Error(DataError.Network.WEBSOCKET_PROTOCOL_ERROR)
            }
        } catch (e: SerializationException) {
            Result.Error(DataError.Network.SERIALIZATION)
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }

    override fun observeMessages(): Flow<Result<Message, DataError.Network>> =
        chatSocketService.observeMessages()
            .map { message -> Result.Success(message) }
            .catch { e ->
                emit(
                    when (e) {
                        is IOException -> Result.Error(DataError.Network.NO_INTERNET)
                        is TimeoutException -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                        is WebSocketException ->  {
                            when {
                                e.message?.contains("closed") == true ->
                                    Result.Error(DataError.Network.WEBSOCKET_SESSION_CLOSED)
                                e.message?.contains("protocol") == true ->
                                    Result.Error(DataError.Network.WEBSOCKET_PROTOCOL_ERROR)
                                else -> Result.Error(DataError.Network.UNKNOWN)
                            }
                        }
                        is SerializationException -> Result.Error(DataError.Network.SERIALIZATION)
                        else -> Result.Error(DataError.Network.UNKNOWN)
                    }
                )
            }

    override suspend fun cancelConnection(): Result<Unit, DataError.Network> =
        try {
            chatSocketService.cancelConnection()
            Result.Success(Unit)
        } catch (e: WebSocketException) {
            Result.Error(DataError.Network.WEBSOCKET_SESSION_CLOSED)
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }
}