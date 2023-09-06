package com.puroblast.feature_hotel_booking.ui.hotel_booking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.feature_hotel_booking.databinding.FragmentHotelBookingBinding
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
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class HotelBookingFragment : Fragment(featureHotelBookingR.layout.fragment_hotel_booking) {

    private val binding by viewBinding(FragmentHotelBookingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookingAdapter = setupAdapter()
        bookingAdapter.submitList(listOf(
            HotelInfoItem(BookingDetails()),
            BookingInfoItem(BookingDetails()),
            BuyerInfoItem(),
            TouristInfoItem(),
            TourPaymentInfoItem(BookingDetails()),
            BookHotelBottomItem("Оплатить 198 036")
        ))
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
}
