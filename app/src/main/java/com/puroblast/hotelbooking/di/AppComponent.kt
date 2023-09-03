package com.puroblast.hotelbooking.di

import android.app.Application
import com.puroblast.feature_hotel_details.di.HotelDetailsDependencies
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent : HotelDetailsDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
