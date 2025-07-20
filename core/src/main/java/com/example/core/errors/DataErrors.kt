package com.example.core.errors

import com.example.core.network.RootError

sealed interface DataError: RootError {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,

        WEBSOCKET_HANDSHAKE_FAILED,
        WEBSOCKET_CONNECTION_FAILED,
        WEBSOCKET_PROTOCOL_ERROR,
        WEBSOCKET_SESSION_CLOSED
    }
    enum class Local: DataError {
        DISK_FULL,
        DATA_NOT_FOUND,
        IO_ERROR,
        UNKNOWN
    }
}