package com.demotest.di

import androidx.room.Room
import com.demotest.data.home.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "university_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().universityDao() }
}