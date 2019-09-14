package com.iteo.android_navigation.inject

import androidx.fragment.app.Fragment
import com.iteo.android_navigation.list.ItemsFilterDialog
import com.webcar.app.inject.qualifiers.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DialogBuilder {

    @Binds
    @IntoMap
    @FragmentKey(ItemsFilterDialog::class)
    abstract fun itemsFilterDialog(fragment: ItemsFilterDialog): Fragment
}
