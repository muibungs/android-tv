package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.dataModule
import com.example.myapplication.di.domainModule
import com.example.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    viewModelModule
                )
            )
        }
    }
}