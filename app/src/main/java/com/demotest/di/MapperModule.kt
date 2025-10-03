package com.demotest.di

import com.demotest.data.home.mapper.UniversityMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { UniversityMapper() }
}