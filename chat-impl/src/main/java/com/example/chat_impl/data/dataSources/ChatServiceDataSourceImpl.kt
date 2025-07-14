package com.example.chat_impl.data.dataSources

import com.example.chat_impl.data.remote.services.ChatService
import com.example.chat_impl.domain.Message
import com.example.core.errors.DataError
import com.example.core.network.Result
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

class ChatServiceDataSourceImpl(
    private val chatService: ChatService
): ChatServiceDataSource {
    override suspend fun getAllMessages(): Result<List<Message>, DataError.Network> =
        try {
            val messageList = chatService.getAllMessages().map { it.toMessage() }
            Result.Success(messageList)
        } catch (e: HttpException) {
            when (e.code()) {
                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
                413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        } catch (e: IOException) {
            Result.Error(DataError.Network.NO_INTERNET)
        } catch (e: SerializationException) {
            Result.Error(DataError.Network.SERIALIZATION)
        }
}