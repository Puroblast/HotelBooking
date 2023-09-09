package com.puroblast.feature_hotel_details.presentation.hotel

import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.AboutHotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.BottomItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.HotelItem

class HotelUiStateMapper {

    fun map(state: HotelDetailsState): HotelUiState {
        if (state.hotel == null) {
            return HotelUiState(emptyList())
        }

        val hotelItem = HotelItem(value = state.hotel)
        val aboutHotelItem = AboutHotelItem(value = state.hotel.aboutTheHotel)
        val bottomItem = BottomItem(value = state.hotel)

        val items = listOf(
            hotelItem,
            aboutHotelItem,
            bottomItem
        )
        return HotelUiState(items)
    }
}
