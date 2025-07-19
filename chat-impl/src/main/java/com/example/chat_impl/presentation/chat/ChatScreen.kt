package com.example.chat_impl.presentation.chat

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat_impl.di.ChatComponent
import com.example.chat_impl.di.ChatDepsProvider
import com.example.chat_impl.presentation.message.OwnMessage
import com.example.design_system.R
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.gigachad_wallpaper_1),
                contentScale = ContentScale.Crop,
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(chatState.value.messagesList) { message ->
                    OwnMessage(
                        text = message.text,
                        time = message.time,
                        true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
    }

}