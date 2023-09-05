package com.puroblast.domain_hotel

import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.domain_hotel.model.Hotel
import com.puroblast.domain_hotel.model.Rooms

class HotelRepository(private val hotelApi: HotelApi) {

    suspend fun getHotel(): Hotel {
        return hotelApi.getHotelDetails()
    }

    suspend fun getHotelRooms(): Rooms {
        return hotelApi.getHotelRooms()
    }

    suspend fun getBookingDetails(): BookingDetails {
        return hotelApi.getBookingDetails()
    }
}
