package com.demotest

import android.os.Bundle
import com.demotest.presentation.base.BaseActivity
import com.demotest.presentation.home.UniversityFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToFragment(UniversityFragment.newInstance())
    }
}