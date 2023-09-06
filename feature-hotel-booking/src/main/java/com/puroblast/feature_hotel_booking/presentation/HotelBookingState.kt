package com.puroblast.feature_hotel_booking.presentation

import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.domain_hotel.model.Tourist

data class HotelBookingState(
    val bookingDetails: BookingDetails? = null,
    val tourists : List<Tourist> = listOf()
)
