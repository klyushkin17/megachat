package com.example.chat_impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        Text("Chat")
        Spacer(modifier = Modifier.height(100.dp))
    }
}