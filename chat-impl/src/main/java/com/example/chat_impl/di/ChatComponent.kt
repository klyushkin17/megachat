package com.example.chat_impl.di

import dagger.Component
import javax.inject.Scope

@Component(modules = [ChatModule::class])
@ChatScope
interface ChatComponent {

    @Component.Factory
    interface Factory {

        fun create(): ChatComponent
    }

    companion object {

    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ChatScope