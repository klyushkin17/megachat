package com.example.megachat.ui

import com.squareup.moshi.Json

data class UserDto(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "firstname") val name: String,
    @field:Json(name = "surname") val username: String,
)
