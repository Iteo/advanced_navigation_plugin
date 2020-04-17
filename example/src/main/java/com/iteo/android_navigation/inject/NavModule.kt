package com.iteo.android_navigation.inject

import com.iteo.android_navigation.inject.factories.DaggerNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavModule {
    @ContributesAndroidInjector(modules = [FragmentBuilder::class, DialogBuilder::class])
    abstract fun navHostFragmentInjector(): DaggerNavHostFragment
}
