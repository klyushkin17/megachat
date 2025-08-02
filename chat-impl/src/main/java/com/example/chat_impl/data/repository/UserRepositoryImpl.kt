package com.example.chat_impl.data.repository

import android.content.Context
import com.example.chat_impl.data.dataSources.UserDataSource
import com.example.chat_impl.domain.repository.UserRepository
import com.example.chat_impl.presentation.errors.ChatUiErrors
import com.example.core.errors.DataError
import com.example.core.network.Result
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun getUserId(
        context: Context
    ): Result<String, ChatUiErrors> {
        val userResult = userDataSource.getUserId(context)

        return when(userResult) {
            is Result.Success -> {
                Result.Success(userResult.data)
            }
            is Result.Error -> {
                when (userResult.error) {
                    DataError.Local.DATA_NOT_FOUND -> Result.Error(ChatUiErrors.AUTH_ERROR)
                    else -> Result.Error(ChatUiErrors.OTHER_ERROR)
                }
            }
        }
    }
}