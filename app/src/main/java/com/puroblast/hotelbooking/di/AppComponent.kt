package com.puroblast.hotelbooking.di

import android.app.Application
import com.puroblast.domain_hotel.HotelApi
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {

    @[Provides AppScope]
    fun provideHotelApi() : HotelApi = HotelApi()
}

@Scope
annotation class AppScope
