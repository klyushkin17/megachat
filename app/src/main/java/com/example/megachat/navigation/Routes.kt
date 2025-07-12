package com.example.megachat.navigation

import kotlinx.serialization.Serializable


sealed interface Routes {

    @Serializable data object ChatScreen: Routes

    @Serializable data object ChatListScreen: Routes
}
