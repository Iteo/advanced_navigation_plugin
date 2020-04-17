package com.iteo.android_navigation

import com.iteo.android_navigation.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}

val JUST_LOG_THROWABLE = { error: Throwable -> Timber.e(error) }
