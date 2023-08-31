package com.puroblast.domain_hotel

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.domain_hotel.model.Hotel
import com.puroblast.domain_hotel.model.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://run.mocky.io/"
interface HotelApi {

    @GET("/v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelDetails() : Hotel

    @GET("v3/f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRoomDetails() : List<Room>

    @GET("v3/e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBookingDetails() : BookingDetails
}

fun HotelApi() : HotelApi {

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat("yyyy-MM-dd")
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
