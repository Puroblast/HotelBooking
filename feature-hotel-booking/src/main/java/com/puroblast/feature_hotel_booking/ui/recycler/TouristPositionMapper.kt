package com.puroblast.feature_hotel_booking.ui.recycler

class TouristPositionMapper {
    fun map(id: Int): String {
        return when (id) {
            1 -> "Первый турист"
            2 -> "Второй турист"
            3 -> "Третий турист"
            4 -> "Четвертый турист"
            else -> "Турист"
        }
    }
}
