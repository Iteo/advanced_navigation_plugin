package com.iteo.android_navigation.filterablelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iteo.android_navigation.extensions.isEmpty
import javax.inject.Inject

class FiltersViewModel @Inject constructor(): ViewModel() {

    private val mutableViewState = MutableLiveData<FiltersState>()
    val viewState: LiveData<FiltersState> = mutableViewState

    fun setInitialValue(color: Int?) {
        if(viewState.isEmpty()) {
            mutableViewState.value = FiltersState(color)
        }
    }

    fun setColor(color: Int) {
        mutableViewState.value = FiltersState(color)
    }

    data class FiltersState(val color: Int?)
}
