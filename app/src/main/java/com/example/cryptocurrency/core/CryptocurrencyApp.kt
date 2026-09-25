package com.example.cryptocurrency.core

import android.app.Application
import com.example.cryptocurrency.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CryptocurrencyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptocurrencyApp)
            modules(appModule)
        }
    }
}