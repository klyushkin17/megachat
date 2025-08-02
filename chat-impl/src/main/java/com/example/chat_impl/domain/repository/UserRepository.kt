package com.example.chat_impl.domain.repository

import android.content.Context
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.core.network.Result

interface UserRepository {

    suspend fun getUserId(context: Context): Result<String, ChatUiErrors>
}