package com.example.chat_impl.di

import com.example.chat_impl.presentation.chat.ChatViewModel
import dagger.Component
import javax.inject.Scope

@Component(
    modules = [ChatModule::class],
    dependencies = [ChatDepsProvider::class]
)
@ChatScope
interface ChatComponent {

    fun getChatViewModelFactoryFactory(): ChatViewModel.ChatViewModelFactory.Factory

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
            if (chatComponent == null) {
                chatComponent = DaggerChatComponent
                    .factory()
                    .create(chatDepsProvider)
            }

            return requireNotNull(chatComponent)
        }
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ChatScope