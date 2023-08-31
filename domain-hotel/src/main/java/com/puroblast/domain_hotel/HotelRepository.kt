package com.puroblast.domain_hotel

import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.domain_hotel.model.Hotel
import com.puroblast.domain_hotel.model.Room

class HotelRepository(private val hotelApi: HotelApi) {

    suspend fun getHotel(): Hotel {
        return hotelApi.getHotelDetails()
    }

    suspend fun getHotelRooms(): List<Room> {
        return hotelApi.getRoomDetails()
    }

    suspend fun getBookingDetails(): BookingDetails {
        return hotelApi.getBookingDetails()
    }
}
