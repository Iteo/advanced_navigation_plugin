package com.iteo.android_navigation.inject.factories

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DaggerNavHostFragment : NavHostFragment() {

    @Inject lateinit var daggerFragmentInjectionFactory: DaggerFragmentFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = daggerFragmentInjectionFactory
        super.onCreate(savedInstanceState)
    }
}
