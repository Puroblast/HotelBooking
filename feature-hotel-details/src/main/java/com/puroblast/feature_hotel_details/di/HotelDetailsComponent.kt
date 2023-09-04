package com.puroblast.feature_hotel_details.di

import com.puroblast.feature_hotel_details.ui.HotelDetailsFragment
import dagger.Component

@Component(dependencies = [HotelDetailsDependencies::class])
internal interface HotelDetailsComponent {

    fun inject(fragment: HotelDetailsFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(hotelDetailsDependencies: HotelDetailsDependencies): Builder

        fun build(): HotelDetailsComponent
    }
}
