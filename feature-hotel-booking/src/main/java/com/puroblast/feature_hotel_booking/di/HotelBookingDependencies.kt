package com.puroblast.feature_hotel_booking.di

import com.puroblast.domain_hotel.HotelRepository

interface HotelBookingDependencies {
    val hotelRepository: HotelRepository
}
