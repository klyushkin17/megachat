package com.example.megachat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chat_impl.presentation.ChatScreen
import com.example.chat_impl.presentation.ChatViewModel
import com.example.chat_list_impl.presentation.ChatListScreen
import com.example.megachat.navigation.Routes
import com.example.megachat.ui.UserDto
import com.example.megachat.ui.theme.MegaChatTheme
import dagger.Lazy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MegaChatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                       navController = navController,
                       startDestination = Routes.ChatListScreen,
                    ){
                        composable<Routes.ChatListScreen> {
                            ChatListScreen(
                                padding = innerPadding,
                                onNavigateToChatButtonClick = {
                                    navController.navigate(Routes.ChatScreen)
                                }
                            )
                        }
                        composable<Routes.ChatScreen> {
                            ChatScreen(
                                padding = innerPadding,
                                chatDepsProvider = (application as MegaChatApplication).appComponent,
                                "FUCK_TOKEN"
                            )
                        }
                    }
                }
            }
        }
    }
}