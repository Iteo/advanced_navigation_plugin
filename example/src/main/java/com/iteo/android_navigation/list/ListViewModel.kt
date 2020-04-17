package com.iteo.android_navigation.list

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iteo.android_navigation.Data
import javax.inject.Inject

class ListViewModel @Inject constructor(): ViewModel() {

    private val mutableViewState = MutableLiveData<ShowList>().apply { value = ShowList(Data.items) }
    val viewState: LiveData<ShowList> = mutableViewState

    fun search(color: Int?) {
        mutableViewState.value = viewState.value?.copy(
            items = if(color == null) Data.items else Data.items.filter { it.color == color },
            colorFilter = color
        )
    }

    data class ShowList(val items: List<Item>, val colorFilter: Int? = null)
}
