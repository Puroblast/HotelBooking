package com.puroblast.domain_hotel

import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.domain_hotel.model.Hotel
import com.puroblast.domain_hotel.model.Room
import retrofit2.http.GET

interface HotelApi {

    @GET("v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelDetails(): Hotel

    @GET("v3/f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getHotelRooms(): List<Room>

    @GET("v3/e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBookingDetails(): BookingDetails
}
