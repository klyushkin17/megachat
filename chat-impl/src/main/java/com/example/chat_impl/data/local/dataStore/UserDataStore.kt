package com.example.chat_impl.data.local.dataStore

interface UserDataStore {

    suspend fun getUserId(): String
}