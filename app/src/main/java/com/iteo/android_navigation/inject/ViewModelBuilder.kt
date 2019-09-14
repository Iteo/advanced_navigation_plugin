package com.iteo.android_navigation.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iteo.android_navigation.entry.EntryViewModel
import com.iteo.android_navigation.filterablelist.FiltersViewModel
import com.iteo.android_navigation.inject.factories.ViewModelProviderFactory
import com.iteo.android_navigation.list.ListViewModel
import com.webcar.app.inject.qualifiers.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(EntryViewModel::class)
    abstract fun entryViewModel(viewModel: EntryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun listViewModel(viewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    abstract fun filtersViewModel(viewModel: FiltersViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}
