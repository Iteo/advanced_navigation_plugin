package com.iteo.android_navigation.item_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.iteo.android_navigation.R
import com.iteo.android_navigation.extensions.observeNotNull
import com.iteo.android_navigation.inject.AssistedViewModelFactory
import kotlinx.android.synthetic.main.fragment_item_details.color
import kotlinx.android.synthetic.main.fragment_item_details.description
import kotlinx.android.synthetic.main.fragment_item_details.title
import javax.inject.Inject

class ItemDetailsFragment @Inject constructor(
    private val vmFactory: ItemDetailsViewModel.Factory
) : Fragment(R.layout.fragment_item_details) {

    private val args: ItemDetailsFragmentArgs by navArgs()

    private val viewModel: ItemDetailsViewModel by viewModels {
        AssistedViewModelFactory { vmFactory.create(args.itemId) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observeNotNull(viewLifecycleOwner) { state ->
            title.text = state.name
            color.setBackgroundColor(state.color)
        }
    }
}
