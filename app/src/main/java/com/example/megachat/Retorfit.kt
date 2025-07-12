package com.example.megachat

import com.example.megachat.ui.UserDto
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

    companion object {
        const val API_KEY = "AIzaSyCEsr9Jp8601qHaitM4CcYCXKV-ByawaBI"
        const val BASE_URL = "https://megachat-kostyaskorik.amvera.io/api/"
    }
}