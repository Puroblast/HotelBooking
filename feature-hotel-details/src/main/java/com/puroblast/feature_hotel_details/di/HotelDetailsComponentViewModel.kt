package com.puroblast.feature_hotel_details.di

import androidx.lifecycle.ViewModel

internal class HotelDetailsComponentViewModel : ViewModel() {
    val hotelDetailsComponent = DaggerHotelDetailsComponent.builder()
        .dependencies(HotelDetailsDependenciesProvider.dependencies)
        .build()
}
