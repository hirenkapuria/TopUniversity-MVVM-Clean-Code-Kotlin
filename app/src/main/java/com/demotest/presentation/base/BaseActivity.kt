package com.demotest.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.demotest.R
import com.demotest.databinding.ActivityBaseBinding

open class BaseActivity : AppCompatActivity(){

    private val binding: ActivityBaseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun navigateToFragment(fragment: Fragment,
                           addToBackStack: Boolean = true,
                           replace: Boolean = false) {
            supportFragmentManager.beginTransaction()
                .apply {
                    if (replace) {
                        replace(R.id.base_container, fragment)
                    } else {
                        add(R.id.base_container, fragment)
                    }
                    if (addToBackStack) addToBackStack(fragment::javaClass.name)
                }
                .commit()
    }
}