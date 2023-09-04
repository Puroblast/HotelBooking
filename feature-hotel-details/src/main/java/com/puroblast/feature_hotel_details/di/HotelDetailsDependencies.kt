package com.puroblast.feature_hotel_details.di

import com.puroblast.domain_hotel.HotelRepository

interface HotelDetailsDependencies {
    val hotelRepository: HotelRepository
}
