package com.example.megachat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chat_impl.presentation.chat.ChatScreen
import com.example.chat_list_impl.presentation.ChatListScreen
import com.example.design_system.theme.BaseAppTheme
import com.example.megachat.navigation.Routes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BaseAppTheme {
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
                                "FUCK_TOKEN",
                                application as Context,
                            )
                        }
                    }
                }
            }
        }
    }
}