package com.puroblast.feature_hotel_booking.ui.hotel_booking

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.feature_hotel_booking.databinding.FragmentHotelBookingBinding
import com.puroblast.feature_hotel_booking.di.HotelBookingComponentViewModel
import com.puroblast.feature_hotel_booking.presentation.HotelBookingViewModel
import com.puroblast.feature_hotel_booking.ui.HotelBookingUiStateMapper
import com.puroblast.feature_hotel_booking.ui.recycler.delegate.BookingInfoAdapterDelegate
import com.puroblast.feature_hotel_booking.ui.recycler.delegate.BottomAdapterDelegate
import com.puroblast.feature_hotel_booking.ui.recycler.delegate.BuyerInfoAdapterDelegate
import com.puroblast.feature_hotel_booking.ui.recycler.delegate.HotelInfoAdapterDelegate
import com.puroblast.feature_hotel_booking.ui.recycler.delegate.TourPaymentInfoAdapterDelegate
import com.puroblast.feature_hotel_booking.ui.recycler.delegate.TouristInfoAdapterDelegate
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookHotelBottomItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookingInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.HotelInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TourPaymentInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TouristInfoItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class HotelBookingFragment : Fragment(featureHotelBookingR.layout.fragment_hotel_booking) {

    private val binding by viewBinding(FragmentHotelBookingBinding::bind)

    private val uiStateMapper = HotelBookingUiStateMapper()

    @Inject
    internal lateinit var hotelBookingViewModelFactory: Lazy<HotelBookingViewModel.Factory>

    private val hotelBookingViewModel: HotelBookingViewModel by viewModels {
        hotelBookingViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<HotelBookingComponentViewModel>()
            .hotelBookingComponent.inject(this)

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookingAdapter = setupAdapter()
        render(bookingAdapter)
    }

    private fun setupAdapter(): CommonAdapter {
        val bookingAdapter = CommonAdapter().apply {
            addDelegate(BookingInfoAdapterDelegate())
            addDelegate(TouristInfoAdapterDelegate())
            addDelegate(BottomAdapterDelegate())
            addDelegate(BuyerInfoAdapterDelegate())
            addDelegate(HotelInfoAdapterDelegate())
            addDelegate(TourPaymentInfoAdapterDelegate())
        }
        binding.recycler.adapter = bookingAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        return bookingAdapter
    }

    private fun render(adapter: CommonAdapter) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                hotelBookingViewModel.state.collect{state->
                    val uiState = uiStateMapper.map(requireContext(), state)
                    adapter.submitList(uiState.items)
                }

            }
        }

    }

}