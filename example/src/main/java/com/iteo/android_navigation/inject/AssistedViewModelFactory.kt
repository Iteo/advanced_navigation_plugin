package com.iteo.android_navigation.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AssistedViewModelFactory(private val creator: () -> ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}
