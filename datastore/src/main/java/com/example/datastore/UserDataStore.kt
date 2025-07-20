package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.userDataStore by preferencesDataStore(name = "user_prefs")

object UserDataStoreKeys {
    val USER_ID = stringPreferencesKey("user_id")
}
