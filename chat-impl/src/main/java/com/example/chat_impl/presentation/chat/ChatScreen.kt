package com.example.chat_impl.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat_impl.di.ChatComponent
import com.example.chat_impl.di.ChatDepsProvider
import com.example.chat_impl.presentation.message.OwnMessage
import kotlinx.coroutines.Dispatchers

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

    val chatState = chatViewModel.chatState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        Text("Chat")
        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(chatState.value.messagesList) { message ->
                OwnMessage(
                    text = message.text,
                    time = message.time,
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }

}