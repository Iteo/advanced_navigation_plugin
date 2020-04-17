package com.iteo.android_navigation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.let(observer) })
}

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { event -> event?.consume()?.run(observer) })
}

fun LiveData<*>.isEmpty() = this.value == null

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> {
    val mediatorLiveData: MediatorLiveData<T> = MediatorLiveData()
    var latestValue: T? = null
    mediatorLiveData.addSource(this) { newValue ->
        if (latestValue != newValue) {
            mediatorLiveData.value = newValue
            latestValue = newValue
        }
    }
    return mediatorLiveData
}
