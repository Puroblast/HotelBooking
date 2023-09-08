package com.puroblast.domain_hotel.model

data class Tourist(
    val id : Int,
    val name: String = "",
    val surname: String = "",
    val birthdayDate: String = "",
    val citizenShip: String = "",
    val passportNumber: String = "",
    val passportExpiresDate: String = ""
)
