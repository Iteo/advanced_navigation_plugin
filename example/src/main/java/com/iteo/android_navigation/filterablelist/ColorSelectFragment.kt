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
import kotlinx.android.synthetic.main.fragment_color_filters.blue
import kotlinx.android.synthetic.main.fragment_color_filters.green
import kotlinx.android.synthetic.main.fragment_color_filters.red
import javax.inject.Inject

class ColorSelectFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var filtersViewModel: FiltersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_color_filters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val filtersVmStore = findNavController().getViewModelStoreOwner(R.id.filters_nav_graph)
        filtersViewModel = ViewModelProvider(filtersVmStore, viewModelFactory)[FiltersViewModel::class.java]
//        filtersViewModel.viewState.observeNotNull(viewLifecycleOwner) { filtersState ->
//            if (red.isChecked != (filtersState.color == Color.RED)) {
//                red.isChecked = filtersState.color == Color.RED
//            }
//
//            if (green.isChecked != (filtersState.color == Color.GREEN)) {
//                green.isChecked = filtersState.color == Color.GREEN
//            }
//
//            if (blue.isChecked != (filtersState.color == Color.BLUE)) {
//                blue.isChecked = filtersState.color == Color.BLUE
//            }
//        }

        red.setOnCheckedChangeListener { compoundButton, checked ->
            filtersViewModel.setColor(Color.RED)
            findNavController().popBackStack()
        }
        green.setOnCheckedChangeListener { compoundButton, checked ->
            filtersViewModel.setColor(Color.GREEN)
            findNavController().popBackStack()
        }
        blue.setOnCheckedChangeListener { compoundButton, checked ->
            filtersViewModel.setColor(Color.BLUE)
            findNavController().popBackStack()
        }
    }
}
