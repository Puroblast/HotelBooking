package com.puroblast.feature_hotel_details.ui.hotel_details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.feature_hotel_details.databinding.FragmentDetailsHotelBinding
import com.puroblast.feature_hotel_details.di.HotelDetailsComponentViewModel
import com.puroblast.feature_hotel_details.presentation.hotel.HotelDetailsViewModel
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR
import com.puroblast.feature_hotel_details.ui.recycler.delegate.hotel.AboutHotelAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.delegate.hotel.HotelAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.AboutHotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.HotelItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelDetailsFragment : Fragment(featureHotelDetailsR.layout.fragment_details_hotel) {

    private val binding by viewBinding(FragmentDetailsHotelBinding::bind)
    private val args: Bundle = bundleOf()

    @Inject
    internal lateinit var hotelDetailsViewModelFactory: Lazy<HotelDetailsViewModel.Factory>

    private val hotelDetailsViewModel: HotelDetailsViewModel by viewModels {
        hotelDetailsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<HotelDetailsComponentViewModel>().hotelDetailsComponent.inject(
            this
        )

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotelAdapter = setupAdapter()
        binding.recycler.adapter = hotelAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        render(hotelAdapter)

        binding.chooseRoomButton.setOnClickListener {
            findNavController().navigate(
                featureHotelDetailsR.id.action_hotelDetailsFragment_to_roomsFragment, args
            )
        }
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
                    args.putString("hotelName", state.hotel.name)
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
