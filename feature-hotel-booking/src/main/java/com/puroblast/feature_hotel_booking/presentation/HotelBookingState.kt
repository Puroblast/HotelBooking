package com.puroblast.feature_hotel_booking.presentation

import com.puroblast.domain_hotel.model.Tourist
import java.math.BigDecimal

data class HotelBookingState(
    val id: Int = 0,
    val hotelName: String = "",
    val hotelAddress: String = "",
    val hotelRating: Int = 0,
    val ratingName: String = "",
    val departure: String = "",
    val arrivalCountry: String = "",
    val tourDateStart: String = "",
    val tourDateStop: String = "",
    val numberOfNights: Int = 0,
    val room: String = "",
    val nutrition: String = "",
    val tourPrice: BigDecimal = BigDecimal(0),
    val fuelCharge: BigDecimal = BigDecimal(0),
    val serviceCharge: BigDecimal = BigDecimal(0),
    val tourists : List<Tourist> = listOf()
)
