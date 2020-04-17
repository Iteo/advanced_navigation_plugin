package com.iteo.android_navigation.inject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.iteo.android_navigation.dashboard.DashboardFragment
import com.iteo.android_navigation.entry.EntryFragment
import com.iteo.android_navigation.filterablelist.ColorSelectFragment
import com.iteo.android_navigation.filterablelist.FilterableListFragment
import com.iteo.android_navigation.filterablelist.ListFiltersFragment
import com.iteo.android_navigation.inject.factories.DaggerFragmentFactory
import com.iteo.android_navigation.item_details.ItemDetailsFragment
import com.iteo.android_navigation.list.ListFragment
import com.iteo.android_navigation.login.LoginFragment
import com.iteo.android_navigation.notifiactions.NotificationsFragment
import com.webcar.app.inject.qualifiers.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {

    @Binds
    abstract fun bindFragmentFactory(factory: DaggerFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(EntryFragment::class)
    abstract fun entryFragment(fragment: EntryFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class)
    abstract fun loginFragment(fragment: LoginFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DashboardFragment::class)
    abstract fun dashboardFragment(fragment: DashboardFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ListFragment::class)
    abstract fun listFragment(fragment: ListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(NotificationsFragment::class)
    abstract fun notificationsFragment(fragment: NotificationsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(FilterableListFragment::class)
    abstract fun filterableListFragment(fragment: FilterableListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ListFiltersFragment::class)
    abstract fun listFilters(fragment: ListFiltersFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ColorSelectFragment::class)
    abstract fun colorSelect(fragment: ColorSelectFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ItemDetailsFragment::class)
    abstract fun itemDetailsFragment(fragment: ItemDetailsFragment): Fragment
}
