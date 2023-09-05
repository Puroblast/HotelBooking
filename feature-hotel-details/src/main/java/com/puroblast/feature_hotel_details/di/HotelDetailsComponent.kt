package com.puroblast.feature_hotel_details.di

import com.puroblast.feature_hotel_details.ui.hotel_details.HotelDetailsFragment
import com.puroblast.feature_hotel_details.ui.room_details.RoomsFragment
import dagger.Component

@Component(dependencies = [HotelDetailsDependencies::class])
internal interface HotelDetailsComponent {

    fun inject(fragment: HotelDetailsFragment)

    fun inject(fragment: RoomsFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(hotelDetailsDependencies: HotelDetailsDependencies): Builder

        fun build(): HotelDetailsComponent
    }
}
