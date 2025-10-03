package com.demotest.di

import com.demotest.domin.university.usecase.GetLocalPagingUniversityListUseCase
import com.demotest.domin.university.usecase.GetLocalUniversityListUseCase
import com.demotest.domin.university.usecase.GetUniversityListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUniversityListUseCase(get()) }
    factory { GetLocalUniversityListUseCase(get()) }
    factory { GetLocalPagingUniversityListUseCase(get()) }
}