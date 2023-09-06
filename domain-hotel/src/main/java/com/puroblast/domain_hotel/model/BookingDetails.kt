package com.puroblast.domain_hotel.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class BookingDetails(
    val id: Int = 0,
    val hotelName: String = "",
    @SerializedName("hotel_adress")
    val hotelAddress: String = "",
    @SerializedName("horating")
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
    val serviceCharge: BigDecimal = BigDecimal(0)
)
