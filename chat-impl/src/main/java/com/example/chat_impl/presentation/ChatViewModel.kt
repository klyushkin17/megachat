package com.example.chat_impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chat_impl.di.ChatComponent
import com.example.chat_impl.di.ChatDepsProvider
import com.example.chat_impl.di.DaggerChatComponent
import com.example.chat_impl.domain.useCases.GetMessagesUseCase
import com.example.chat_impl.domain.useCases.SendMessageUseCase
import com.example.chat_impl.domain.useCases.StartConnectionUseCase
import com.example.chat_impl.domain.useCases.StopConnectionUseCase
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.core.factory.ViewModelFactoryFactory
import com.example.core.network.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
    private val backgroundTaskDispatcher: CoroutineDispatcher,
    private val authToken: String,
): ViewModel() {

    private val backgroundCoroutineContext = backgroundTaskDispatcher + SupervisorJob()

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    private val _messageTextFieldState = MutableStateFlow("")
    val messageTextFieldState = _messageTextFieldState.asStateFlow()

    init {
        getMessages()
        startConnection()
    }

    fun onAction(action: ChatActions) =
        when(action) {
            is ChatActions.OnLeaveChat -> stopConnections()
            is ChatActions.OnMessageSend -> sendMessage(action.message)
            is ChatActions.OnMessageChange -> onMessageChange(action.message)
        }

    private fun startConnection() {
        viewModelScope.launch(backgroundCoroutineContext) {
            startConnectionUC(authToken)
                .collect{ messageResult ->
                    when (messageResult) {
                        is Result.Success -> {
                            val newMessage = messageResult.data
                            _chatState.update {
                                chatState.value.copy(
                                    messagesList = chatState.value.messagesList + newMessage
                                )
                            }
                        }

                        is Result.Error -> {
                            if (messageResult.error == ChatUiErrors.NO_INTERNET_CONNECTION) {
                                _chatState.update {
                                    chatState.value.copy(error = ChatError.NoInternetConnection)
                                }
                            }
                        }
                    }

                }
        }
    }

    private fun stopConnections() {
        viewModelScope.launch(backgroundCoroutineContext) {
            val stoppingResult = stopConnectionUC()

            when(stoppingResult) {
                is Result.Success -> { Log.d(TEMP_TAG, "Connection closed") }
                is Result.Error -> stoppingResult.error.handleErrors()
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch(backgroundCoroutineContext) {
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
            startLoading()

            val messagesListResult = getMessagesUC()

            when(messagesListResult) {
                is Result.Success -> {
                    _chatState.update {
                        chatState.value.copy( messagesList = messagesListResult.data )
                    }

                    if (chatState.value.error == ChatError.NoInternetConnection) {
                        _chatState.update { chatState.value.copy(error = ChatError.None) }
                    }
                }
                is Result.Error -> messagesListResult.error.handleErrors()
            }

            stopLoading()
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

    private fun onMessageChange(message: String) {
        _messageTextFieldState.update { message }
    }

    private fun startLoading() =
        _chatState.update { chatState.value.copy(isLoading = true) }

    private fun stopLoading() =
        _chatState.update { chatState.value.copy(isLoading = false) }

    @Suppress("UNCHECKED_CAST")
    class ChatViewModelFactory @AssistedInject constructor(
        private val sendMessageUC: SendMessageUseCase,
        private val getMessagesUC: GetMessagesUseCase,
        private val startConnectionUC: StartConnectionUseCase,
        private val stopConnectionUC: StopConnectionUseCase,
        @Assisted("backgroundTaskDispatcher")
        private val backgroundTaskDispatcher: CoroutineDispatcher,
        @Assisted("chatDepsProvider")
        private val chatDepsProvider: ChatDepsProvider,
        @Assisted("authToken")
        private val authToken: String,
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChatViewModel(
                sendMessageUC,
                getMessagesUC,
                startConnectionUC,
                stopConnectionUC,
                backgroundTaskDispatcher,
                authToken
            ) as T
        }

        @AssistedFactory
        interface Factory: ViewModelFactoryFactory {

            fun create(
                @Assisted("backgroundTaskDispatcher") backgroundTaskDispatcher: CoroutineDispatcher,
                @Assisted("authToken") authToken: String,
            ): ChatViewModelFactory
        }
    }
}
