package com.example.core_network_impl.di

import dagger.Component
import javax.inject.Scope


@Component(modules = [NetworkModule::class])
@NetworkScope
interface NetworkComponent {

    @Component.Factory
    interface Factory {

        fun create(): NetworkComponent
    }

    companion object {

    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScope