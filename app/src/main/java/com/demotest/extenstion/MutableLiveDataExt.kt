package com.demotest.extenstion

import androidx.lifecycle.MutableLiveData

inline fun <T> MutableLiveData<T>.update(function: (T) -> T) {
    value = function(value!!)
}