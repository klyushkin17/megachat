package com.example.chat_list_impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

@Composable
fun ChatListScreen(
    padding: PaddingValues,
    onNavigateToChatButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        Text("Chat List")
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = onNavigateToChatButtonClick
        ) {
            Text("To Chat")
        }
    }
}