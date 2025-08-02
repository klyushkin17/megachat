package com.example.chat_impl.presentation.errors

import com.example.core.network.RootError

enum class ChatUiErrors: RootError {
    NO_INTERNET_CONNECTION,
    SEND_MESSAGE_ERROR,
    AUTH_ERROR,
    OTHER_ERROR,
}