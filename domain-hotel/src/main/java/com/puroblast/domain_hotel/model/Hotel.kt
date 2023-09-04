package com.puroblast.domain_hotel.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Hotel(
    val id: Int = 0,
    val name: String = "",
    @SerializedName("adress")
    val address: String = "",
    val minimalPrice: BigDecimal = BigDecimal(0),
    val priceForIt: String = "",
    val rating: Int = 0,
    val ratingName: String = "",
    val imageUrls: List<String> = listOf(),
    @SerializedName("about_the_hotel")
    val aboutTheHotel: AboutTheHotel = AboutTheHotel()
)
