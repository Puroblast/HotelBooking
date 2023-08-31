package com.puroblast.domain_hotel.model

import com.google.gson.annotations.SerializedName

data class Hotel(
    val id : Int,
    val name : String,
    val adress : String,
    val minimalPrice : Int,
    val priceForIt : String,
    val rating : Int,
    val ratingName : String,
    val imageUrls : List<String>,
    @SerializedName("about_the_hotel") val aboutTheHotel : AboutTheHotel
)
