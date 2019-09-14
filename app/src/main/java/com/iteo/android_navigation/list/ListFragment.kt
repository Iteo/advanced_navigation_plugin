package com.iteo.android_navigation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.iteo.android_navigation.R
import com.iteo.android_navigation.extensions.navigate
import com.iteo.android_navigation.extensions.observeNotNull
import kotlinx.android.synthetic.main.fragment_list.filter
import kotlinx.android.synthetic.main.fragment_list.list
import javax.inject.Inject

class ListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ItemAdapter()
        list.adapter = adapter
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ListViewModel::class.java]
        viewModel.viewState.observeNotNull(this) { state ->
            adapter.submitList(state.items)
            filter.setOnClickListener {
                navigate(ListFragmentDirections.openFilters(currentColor = state.colorFilter ?: -1))
            }
        }
    }
}
