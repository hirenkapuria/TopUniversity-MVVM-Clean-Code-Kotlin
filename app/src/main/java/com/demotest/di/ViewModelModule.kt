package com.demotest.di

import com.demotest.presentation.home.UniversityViewModel
import com.demotest.presentation.universityDetails.UniversityDetailsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel { UniversityViewModel(get(),get(), get()) }
    viewModel { UniversityDetailsFragmentViewModel(get()) }
}