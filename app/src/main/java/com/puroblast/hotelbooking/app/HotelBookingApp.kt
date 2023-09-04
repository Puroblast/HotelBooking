package com.puroblast.hotelbooking.app

import android.app.Application
import com.puroblast.feature_hotel_details.di.HotelDetailsDependenciesStore
import com.puroblast.hotelbooking.di.AppComponent
import com.puroblast.hotelbooking.di.DaggerAppComponent

class HotelBookingApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        HotelDetailsDependenciesStore.dependencies = appComponent
    }
}
