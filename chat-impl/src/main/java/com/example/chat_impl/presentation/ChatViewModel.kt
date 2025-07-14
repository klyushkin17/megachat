package com.example.chat_impl.presentation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat_impl.domain.useCases.GetMessagesUseCase
import com.example.chat_impl.domain.useCases.SendMessageUseCase
import com.example.chat_impl.domain.useCases.StartConnectionUseCase
import com.example.chat_impl.domain.useCases.StopConnectionUseCase
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TEMP_TAG = "god damn"

class ChatViewModel(
    private val sendMessageUC: SendMessageUseCase,
    private val getMessagesUC: GetMessagesUseCase,
    private val startConnectionUC: StartConnectionUseCase,
    private val stopConnectionUC: StopConnectionUseCase,
): ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    private val _messageListState = MutableStateFlow<List<MessageUi>>(emptyList())
    val messageListState = _messageListState.asStateFlow()

    init {
        getMessages()
    }

    fun onAction(action: ChatActions) =
        when(action) {
            is ChatActions.OnLeaveChat -> stopConnections()
            is ChatActions.OnMessageSend -> sendMessage(action.message)
            is ChatActions.OnMessageChange -> // TODO
        }

    private fun stopConnections() {
        viewModelScope.launch {
            val stoppingResult = stopConnectionUC()

            when(stoppingResult) {
                is Result.Success -> { Log.d(TEMP_TAG, "Connection closed") }
                is Result.Error -> stoppingResult.error.handleErrors()
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            val sendingResult = sendMessageUC(message)

            when(sendingResult) {
                is Result.Success -> {
                    if (chatState.value.error == ChatError.SendMessageError ||
                        chatState.value.error == ChatError.NoInternetConnection) {
                        _chatState.update { chatState.value.copy(error = ChatError.None) }
                    }
                }
                is Result.Error -> sendingResult.error.handleErrors()
            }
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            val messagesListResult = getMessagesUC()

            when(messagesListResult) {
                is Result.Success -> {
                    _messageListState.update { messagesListResult.data }

                    if (chatState.value.error == ChatError.NoInternetConnection) {
                        _chatState.update { chatState.value.copy(error = ChatError.None) }
                    }
                }
                is Result.Error -> sendingResult.error.handleErrors()
            }
        }
    }

    private fun ChatUiErrors.handleErrors() {
        when(this) {
            ChatUiErrors.OTHER_ERROR -> _chatState.update {
                chatState.value.copy(error = ChatError.UnknownError)
            }
            ChatUiErrors.NO_INTERNET_CONNECTION -> _chatState.update {
                chatState.value.copy(error = ChatError.NoInternetConnection)
            }
            ChatUiErrors.SEND_MESSAGE_ERROR -> _chatState.update {
                chatState.value.copy(error = ChatError.SendMessageError)
            }
        }
    }
}
