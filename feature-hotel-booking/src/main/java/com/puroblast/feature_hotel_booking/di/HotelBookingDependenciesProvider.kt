package com.puroblast.feature_hotel_booking.di

import androidx.annotation.RestrictTo
import kotlin.properties.Delegates

interface HotelBookingDependenciesProvider {

    companion object : HotelBookingDependenciesProvider by HotelBookingDependenciesStore

    @get: RestrictTo(RestrictTo.Scope.LIBRARY)
    val dependencies: HotelBookingDependencies

}

object HotelBookingDependenciesStore : HotelBookingDependenciesProvider {

    override var dependencies: HotelBookingDependencies by Delegates.notNull()
}
