package com.iteo.android_navigation.list

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ListViewModel @Inject constructor(): ViewModel() {

    private val mutableViewState = MutableLiveData<ShowList>().apply { value = ShowList(initData()) }
    val viewState: LiveData<ShowList> = mutableViewState

    fun search(color: Int?) {
        mutableViewState.value = viewState.value?.copy(
            items = if(color == null) initData() else initData().filter { it.color == color },
            colorFilter = color
        )
    }

    data class ShowList(val items: List<Item>, val colorFilter: Int? = null)
}

fun initData() = listOf(
    Item(1, "Test1", Color.RED),
    Item(2, "Test2", Color.GREEN),
    Item(3, "Item", Color.BLUE),
    Item(4, "Item2", Color.RED),
    Item(5, "Nowy", Color.GREEN),
    Item(6, "Nowy2", Color.RED),
    Item(7, "Nowy3", Color.BLUE),
    Item(8, "Red", Color.RED),
    Item(9, "Blue", Color.BLUE),
    Item(10, "Green", Color.GREEN),
    Item(11, "Blue2", Color.BLUE),
    Item(12, "Green2", Color.GREEN),
    Item(13, "Red2", Color.RED)
)
