package com.iteo.android_navigation.filterablelist

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.iteo.android_navigation.R
import com.iteo.android_navigation.extensions.navigate
import com.iteo.android_navigation.extensions.observeNotNull
import com.iteo.android_navigation.list.ListViewModel
import kotlinx.android.synthetic.main.fragment_filters.apply
import kotlinx.android.synthetic.main.fragment_filters.color
import javax.inject.Inject

class ListFiltersFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var filtersViewModel: FiltersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listVmStore = findNavController().getViewModelStoreOwner(R.id.filtered_list_nav_graph)
        val filtersVmStore = findNavController().getViewModelStoreOwner(R.id.filters_nav_graph)
        listViewModel = ViewModelProvider(listVmStore, viewModelFactory)[ListViewModel::class.java]
        filtersViewModel = ViewModelProvider(filtersVmStore, viewModelFactory)[FiltersViewModel::class.java]

        listViewModel.viewState.observeNotNull(viewLifecycleOwner) { state ->
            filtersViewModel.setInitialValue(state.colorFilter)
        }
        filtersViewModel.viewState.observeNotNull(viewLifecycleOwner) { selectedFilters ->
            color.setBackgroundColor(selectedFilters.color ?: Color.GRAY)

            apply.setOnClickListener {
                listViewModel.search(selectedFilters.color)
                findNavController().popBackStack()
            }
        }

        color.setOnClickListener {
            navigate(ListFiltersFragmentDirections.openColorSelector())
        }
    }
}
