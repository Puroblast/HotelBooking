package com.puroblast.feature_hotel_details.di

import androidx.annotation.RestrictTo
import kotlin.properties.Delegates.notNull

interface HotelDetailsDependenciesProvider {
    companion object : HotelDetailsDependenciesProvider by HotelDetailsDependenciesStore

    @get: RestrictTo(RestrictTo.Scope.LIBRARY)
    val dependencies: HotelDetailsDependencies
}

object HotelDetailsDependenciesStore : HotelDetailsDependenciesProvider {

    override var dependencies: HotelDetailsDependencies by notNull()
}
