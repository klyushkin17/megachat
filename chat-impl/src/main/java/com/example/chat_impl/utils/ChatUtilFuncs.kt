package com.example.chat_impl.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import com.example.chat_impl.data.remote.models.MessageDto
import com.example.chat_impl.domain.model.Message
import com.example.design_system.R
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.serialization.json.Json
import kotlin.random.Random

fun Frame.toMessage(): Message {
    val json = (this as? Frame.Text)?.readText() ?: ""
    val messageDto = Json.decodeFromString<MessageDto>(json)
    return messageDto.toMessage()
}

@Composable
fun getRandomGigachadWallpaper(): Painter {
    val randomOfSix = remember { Random.nextInt(1,7) }
    return when (randomOfSix) {
        1 -> painterResource(R.drawable.gigachad_wallpaper_1)
        2 -> painterResource(R.drawable.gigachad_wallpaper_2)
        3 -> painterResource(R.drawable.gigachad_wallpaper_3)
        4 -> painterResource(R.drawable.gigachad_wallpaper_4)
        5 -> painterResource(R.drawable.gigachad_wallpaper_5)
        6 -> painterResource(R.drawable.gigachad_wallpaper_6)
        else -> painterResource(R.drawable.gigachad_wallpaper_1)
    }
}