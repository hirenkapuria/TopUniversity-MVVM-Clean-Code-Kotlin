package com.demotest

import android.app.Application
import com.demotest.di.databaseModule
import com.demotest.di.mapperModule
import com.demotest.di.networkModule
import com.demotest.di.presenterModule
import com.demotest.di.repositoryModule
import com.demotest.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    mapperModule,
                    repositoryModule,
                    presenterModule,
                    useCaseModule
                )
            )
        }

    }
}