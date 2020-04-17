package com.iteo.android_navigation.entry

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.iteo.advanced_navigation_plugin.navigate
import com.iteo.android_navigation.entry.EntryViewModel.EntryState.SHOW_LOGIN
import com.iteo.android_navigation.entry.EntryViewModel.EntryState.SKIP_LOGIN
import com.iteo.android_navigation.extensions.observeNotNull
import javax.inject.Inject

class EntryFragment @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var viewModel: EntryViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)[EntryViewModel::class.java]
        viewModel.viewState.observeNotNull(this) { state-> // nie mozemy dac viewlifecycleownera bo nie ma view...
            when(state) {
                SKIP_LOGIN -> navigate(EntryFragmentDirections.openDashboard())
                SHOW_LOGIN -> navigate(EntryFragmentDirections.openLogin())
            }
        }
    }
}
