package com.example.core_network_impl.di

import com.example.core_network_api.NetworkApi
import dagger.Component
import javax.inject.Scope


@Component(modules = [NetworkModule::class])
@NetworkScope
interface NetworkComponent: NetworkApi {

    @Component.Factory
    interface Factory {

        fun create(): NetworkComponent
    }

    companion object {

        @Volatile
        private var networkComponent: NetworkComponent? = null

        fun init(): NetworkComponent {

            networkComponent?.let {
                networkComponent = DaggerNetworkComponent
                    .factory()
                    .create()
            }

            return requireNotNull(networkComponent)
        }
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScope