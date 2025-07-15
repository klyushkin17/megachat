package com.example.chat_impl.di

import com.example.chat_impl.presentation.ChatViewModel
import dagger.Component
import javax.inject.Scope

@Component(
    modules = [ChatModule::class],
    dependencies = [ChatDepsProvider::class]
)
@ChatScope
interface ChatComponent {

    @Component.Factory
    interface Factory {

        fun create(
            chatDepsProvider: ChatDepsProvider
        ): ChatComponent
    }

    companion object {

        @Volatile
        private var chatComponent: ChatComponent? = null

        fun init(chatDepsProvider: ChatDepsProvider): ChatComponent {
            chatComponent?.let {
                chatComponent = DaggerChatComponent
                    .factory()
                    .create(chatDepsProvider)
            }

            return requireNotNull(chatComponent)
        }
    }

    fun injectIntoChatViewModel(chatViewModel: ChatViewModel)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ChatScope