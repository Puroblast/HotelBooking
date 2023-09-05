package com.puroblast.feature_hotel_details.presentation.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_hotel.HotelRepository
import com.puroblast.domain_hotel.model.Rooms
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomsDetailsViewModel(
    private val hotelRepository: HotelRepository
) : ViewModel() {

    init {
        getHotelRooms()
    }

    private val _state = MutableStateFlow(RoomsDetailsState(Rooms()))
    val state = _state.asStateFlow()

    private fun getHotelRooms() {
        viewModelScope.launch {
            val rooms = hotelRepository.getHotelRooms()
            _state.value = _state.value.copy(rooms = rooms)
        }
    }

    class Factory @Inject constructor(
        private val hotelRepository: HotelRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == RoomsDetailsViewModel::class.java)
            return RoomsDetailsViewModel(hotelRepository) as T
        }
    }
}
