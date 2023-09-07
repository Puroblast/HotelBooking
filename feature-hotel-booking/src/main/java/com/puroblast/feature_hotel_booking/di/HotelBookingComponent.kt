package com.puroblast.feature_hotel_booking.di

import com.puroblast.feature_hotel_booking.ui.hotel_booking.HotelBookingFragment
import dagger.Component

@Component(dependencies = [HotelBookingDependencies::class])
internal interface HotelBookingComponent {

    fun inject(fragment: HotelBookingFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(hotelBookingDependencies: HotelBookingDependencies): Builder

        fun build(): HotelBookingComponent
    }
}
