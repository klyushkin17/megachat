package com.example.megachat.di

import dagger.Component
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope