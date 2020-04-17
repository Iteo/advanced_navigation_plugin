package com.iteo.android_navigation.inject

import android.content.Context
import com.iteo.android_navigation.App
import com.iteo.android_navigation.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(app: App): Context

    @ContributesAndroidInjector(modules = [NavModule::class])
    abstract fun mainActivityInjector(): MainActivity
}
