package com.puroblast.feature_hotel_booking.ui.hotel_booking

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.feature_hotel_booking.databinding.FragmentHotelBookingBinding
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class HotelBookingFragment : Fragment(featureHotelBookingR.layout.fragment_hotel_booking) {

    private val binding by viewBinding(FragmentHotelBookingBinding::bind)

}
