package com.example.megachat

import android.app.Application
import com.example.chat_impl.di.ChatDepsProvider
import com.example.megachat.di.AppComponent
import com.example.megachat.di.DaggerAppComponent

class MegaChatApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create()
    }
}