package com.iteo.android_navigation.filterablelist

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
import com.iteo.android_navigation.list.ItemAdapter
import com.iteo.android_navigation.list.ListViewModel
import kotlinx.android.synthetic.main.fragment_filtered_list.filter
import kotlinx.android.synthetic.main.fragment_filtered_list.list
import javax.inject.Inject

class FilterableListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filtered_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ItemAdapter()
        list.adapter = adapter
        val listVmStore = findNavController().getViewModelStoreOwner(R.id.filtered_list_nav_graph)
        viewModel = ViewModelProvider(listVmStore, viewModelFactory)[ListViewModel::class.java]
        viewModel.viewState.observeNotNull(viewLifecycleOwner) { state ->
            adapter.submitList(state.items)
            filter.setOnClickListener {
                navigate(FilterableListFragmentDirections.openFilters(initialColor = state.colorFilter ?: -1))
            }
        }
    }
}
