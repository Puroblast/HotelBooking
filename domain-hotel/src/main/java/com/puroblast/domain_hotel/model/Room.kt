package com.puroblast.domain_hotel.model

import java.math.BigDecimal

data class Room(
    val id: Int = 0,
    val name: String = "",
    val price: BigDecimal = BigDecimal(0),
    val pricePer: String = "",
    val peculiarities: List<String> = listOf(),
    val imageUrls: List<String> = listOf()
)
