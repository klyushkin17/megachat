package com.example.chat_impl.data.local.dataStore

import android.content.Context
import com.example.datastore.UserDataStoreKeys
import com.example.datastore.userDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(
    private val context: Context
): UserDataStore {

    override suspend fun getUserId(): String =
        context.userDataStore.data
            .map { preferences ->
                preferences[UserDataStoreKeys.USER_ID]
                    ?: throw IllegalStateException("User ID not found")
            }
            .first()
}