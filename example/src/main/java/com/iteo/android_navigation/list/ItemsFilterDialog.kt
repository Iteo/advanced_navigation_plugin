package com.iteo.android_navigation.list

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.iteo.advanced_navigation_plugin.getPreviousFragment
import com.iteo.android_navigation.R
import kotlinx.android.synthetic.main.dialog_filters.blue
import kotlinx.android.synthetic.main.dialog_filters.green
import kotlinx.android.synthetic.main.dialog_filters.red
import javax.inject.Inject

class ItemsFilterDialog @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : DialogFragment() {

    private lateinit var viewModel: ListViewModel

    private val parameters by lazy {
        ItemsFilterDialogArgs.fromBundle(arguments ?: Bundle())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_filters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(getPreviousFragment(), viewModelFactory)[ListViewModel::class.java]
        if (parameters.currentColor != -1) {
            red.isChecked = parameters.currentColor == Color.RED
            green.isChecked = parameters.currentColor == Color.GREEN
            blue.isChecked = parameters.currentColor == Color.BLUE
        }
        red.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                viewModel.search(Color.RED)
                green.isChecked = false
                blue.isChecked = false
            }
            checkForAllUnchecked()
        }
        green.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                viewModel.search(Color.GREEN)
                red.isChecked = false
                blue.isChecked = false
            }
            checkForAllUnchecked()
        }
        blue.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                viewModel.search(Color.BLUE)
                green.isChecked = false
                red.isChecked = false
            }
            checkForAllUnchecked()
        }
    }

    private fun checkForAllUnchecked() {
        if (!green.isChecked && !red.isChecked && !blue.isChecked) {
            viewModel.search(null)
        }
    }
}
