package com.demotest.di

import com.demotest.data.service.UniversityServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory.create
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 60L
private const val SIGNING_TIMEOUT_SECONDS = 3 * 60 + 10L

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = when {
                else -> HttpLoggingInterceptor.Level.BASIC
            }
        }
    }
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://universities.hipolabs.com/")
            .addConverterFactory(create(get()).withNullSerialization())
            .build()
            .create(UniversityServices::class.java)
    }
}