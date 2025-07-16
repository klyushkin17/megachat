package com.example.chat_impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat_impl.di.ChatComponent
import com.example.chat_impl.di.ChatDepsProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@Composable
fun ChatScreen(
    padding: PaddingValues,
    chatDepsProvider: ChatDepsProvider,
    authToken: String,
) {
    val chatViewModel: ChatViewModel = viewModel(
        factory = ChatComponent.init(chatDepsProvider).getChatViewModelFactoryFactory()
            .create(
                Dispatchers.IO,
                authToken
            )
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        Text("Chat")
        Spacer(modifier = Modifier.height(100.dp))
    }
}