package com.puroblast.domain_hotel.model

import com.google.gson.annotations.SerializedName

data class BookingDetails(
    val id: Int,
    val hotelName: String,
    @SerializedName("hotel_adress")
    val hotelAddress: String,
    @SerializedName("horating")
    val hotelRating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int
)
