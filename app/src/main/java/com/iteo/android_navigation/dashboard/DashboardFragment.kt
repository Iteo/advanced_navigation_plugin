package com.iteo.android_navigation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iteo.android_navigation.R
import com.iteo.android_navigation.extensions.setupWithNavController
import kotlinx.android.synthetic.main.fragment_dashboard.bottomNav
import javax.inject.Inject

private val navGraphIds = listOf(
    R.navigation.list_nav_graph,
    R.navigation.notifications_nav_graph,
    R.navigation.filtered_list_nav_graph
)

class DashboardFragment @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory
): Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomNav.setupWithNavController(
            navGraphIds,
            childFragmentManager,
            R.id.fragmentContainer,
            requireActivity().intent
        )
    }
}
