package com.puroblast.feature_hotel_details.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.feature_hotel_details.databinding.FragmentDetailsHotelBinding
import com.puroblast.feature_hotel_details.di.HotelDetailsComponentViewModel
import com.puroblast.feature_hotel_details.presentation.HotelDetailsViewModel
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR
import com.puroblast.feature_hotel_details.ui.recycler.delegate.AboutHotelAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.delegate.HotelAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.model.AboutHotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.HotelItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelDetailsFragment : Fragment(featureHotelDetailsR.layout.fragment_details_hotel) {

    private val binding by viewBinding(FragmentDetailsHotelBinding::bind)

    @Inject
    internal lateinit var hotelDetailsViewModelFactory: Lazy<HotelDetailsViewModel.Factory>

    private val hotelDetailsViewModel: HotelDetailsViewModel by viewModels {
        hotelDetailsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<HotelDetailsComponentViewModel>()
            .hotelDetailsComponent
            .inject(this)

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotelAdapter = setupAdapter()
        render(hotelAdapter)

    }

    private fun setupAdapter(): CommonAdapter {

        val hotelAdapter = CommonAdapter().apply {
            addDelegate(HotelAdapterDelegate())
            addDelegate(AboutHotelAdapterDelegate())
        }
        return hotelAdapter

    }

    private fun render(adapter: CommonAdapter) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                hotelDetailsViewModel.state.collect { state ->
                    val hotelItem = HotelItem(value = state.hotel)
                    val aboutHotelItem = AboutHotelItem(value = state.hotel.aboutTheHotel)
                    adapter.submitList(
                        listOf(
                            hotelItem, aboutHotelItem
                        )
                    )
                }
            }
        }

    }

}
