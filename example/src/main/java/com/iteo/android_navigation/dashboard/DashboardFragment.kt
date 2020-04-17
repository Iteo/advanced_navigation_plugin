package com.iteo.android_navigation.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iteo.advanced_navigation_plugin.BottomBarContainerSetting
import com.iteo.advanced_navigation_plugin.BottomGraphContainer
import com.iteo.advanced_navigation_plugin.handleDeepLink
import com.iteo.advanced_navigation_plugin.setupWithNavController
import com.iteo.android_navigation.R
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_dashboard.bottomNav
import javax.inject.Inject

private val navGraphIds = listOf(
    R.navigation.list_nav_graph,
    R.navigation.notifications_nav_graph,
    R.navigation.filtered_list_nav_graph
)

class DashboardFragment @Inject constructor(
    private val newIntentObservable: Observable<Intent>
) : Fragment(), BottomGraphContainer {

    private var observeNewIntentObservable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomNav.setupWithNavController(
            navGraphIds,
            childFragmentManager,
            R.id.fragmentContainer,
            requireActivity().intent
        )

        observeNewIntentObservable?.dispose()
        observeNewIntentObservable = newIntentObservable
            .subscribeBy(
                onNext = { intent ->
                    bottomNav.handleDeepLink(
                        navGraphIds,
                        childFragmentManager,
                        R.id.fragmentContainer,
                        intent
                    )
                }
            )
    }

    override fun onDestroyView() {
        observeNewIntentObservable?.dispose()
        super.onDestroyView()
    }

    override fun bottomGraphContainerSettings(): BottomBarContainerSetting {
        return BottomBarContainerSetting(navGraphIds, R.id.fragmentContainer, bottomNav)
    }
}
