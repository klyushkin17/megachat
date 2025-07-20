package com.example.chat_impl.data.dataSources

import android.content.Context
import com.example.core.errors.DataError
import com.example.core.network.Result

interface UserDataSource {

    suspend fun getUserId(
        context: Context
    ): Result<String, DataError.Local>
}