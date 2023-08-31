package com.puroblast.hotelbooking.app

import android.app.Application
import com.puroblast.hotelbooking.di.AppComponent
import com.puroblast.hotelbooking.di.DaggerAppComponent

class HotelBookingApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
