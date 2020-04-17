package com.iteo.android_navigation.item_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iteo.android_navigation.Data
import com.iteo.android_navigation.list.Item
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ItemDetailsViewModel @AssistedInject constructor(
    @Assisted itemId: Int
) : ViewModel() {

    private val mutableViewState = MutableLiveData<Item>(Data.itemsMap[itemId])
    val viewState: LiveData<Item> = mutableViewState

    @AssistedInject.Factory
    interface Factory {
        fun create(itemId: Int): ItemDetailsViewModel
    }
}
