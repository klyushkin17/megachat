package com.example.megachat.di

import com.example.chat_impl.di.ChatDepsProvider
import dagger.Component
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent: ChatDepsProvider {

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope