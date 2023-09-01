package com.puroblast.hotelbooking.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.puroblast.domain_hotel.HotelApi
import com.puroblast.domain_hotel.HotelRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://run.mocky.io/"

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideHotelApi(): HotelApi {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val api: HotelApi by lazy {
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(HotelApi::class.java)
        }

        return api
    }

    @[Provides AppScope]
    fun provideHotelRepository(hotelApi: HotelApi): HotelRepository {
        return HotelRepository(hotelApi)
    }
}
