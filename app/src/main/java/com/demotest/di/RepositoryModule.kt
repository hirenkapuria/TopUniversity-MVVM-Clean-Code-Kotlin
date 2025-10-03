package com.demotest.di

import com.demotest.data.home.repository.UniversityRepositoryImpl
import com.demotest.domin.university.repository.UniversityRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UniversityRepository> { UniversityRepositoryImpl(get(),get()) }
}
