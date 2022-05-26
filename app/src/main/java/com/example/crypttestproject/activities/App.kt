package com.example.crypttestproject.activities

import android.app.Application
import com.example.crypttestproject.modules.MainModule
import com.example.crypttestproject.modules.UseCasesModule
import com.example.crypttestproject.modules.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    MainModule.get(this@App),
                    ViewModelsModule.get(),
                    UseCasesModule.get()
                )
            )
        }
    }

}