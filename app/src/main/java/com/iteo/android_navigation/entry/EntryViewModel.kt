package com.iteo.android_navigation.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iteo.android_navigation.entry.EntryViewModel.EntryState.SHOW_LOGIN
import com.iteo.android_navigation.entry.EntryViewModel.EntryState.SKIP_LOGIN
import javax.inject.Inject
import kotlin.random.Random

class EntryViewModel @Inject constructor() : ViewModel() {

    private val mutableViewState = MutableLiveData<EntryState>()
    val viewState: LiveData<EntryState> = mutableViewState

    init {
        mutableViewState.value =
            if (Random.nextBoolean()) {
                SKIP_LOGIN
            } else {
                SHOW_LOGIN
            }
    }

    enum class EntryState {
        SKIP_LOGIN,
        SHOW_LOGIN
    }
}
