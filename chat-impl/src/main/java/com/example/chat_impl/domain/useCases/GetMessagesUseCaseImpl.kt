package com.example.chat_impl.domain.useCases

import android.content.Context
import com.example.chat_impl.domain.repository.ChatServiceRepository
import com.example.chat_impl.domain.repository.UserRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import javax.inject.Inject

class GetMessagesUseCaseImpl @Inject constructor(
    private val chatServiceRepository: ChatServiceRepository,
    private val userRepository: UserRepository,
): GetMessagesUseCase {

    override suspend fun invoke(context: Context): Result<List<MessageUi>, ChatUiErrors> {
        val messagesResult = chatServiceRepository.getAllMessages()
        val userId = userRepository.getUserId(context)

        return when (messagesResult) {
            is Result.Error -> Result.Error(messagesResult.error)
            is Result.Success -> {
                when(userId) {
                    is Result.Success -> {
                        val messageUiList = messagesResult.data.map { it.toMessageUi(userId.data) }
                        Result.Success(messageUiList)
                    }
                    is Result.Error -> Result.Error(userId.error)
                }
            }
        }
    }
}