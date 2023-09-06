package com.puroblast.feature_hotel_booking.di

import androidx.lifecycle.ViewModel

internal class HotelBookingComponentViewModel : ViewModel() {
    val hotelBookingComponent = DaggerHotelBookingComponent.builder()
        .dependencies(HotelBookingDependenciesProvider.dependencies)
        .build()
}
