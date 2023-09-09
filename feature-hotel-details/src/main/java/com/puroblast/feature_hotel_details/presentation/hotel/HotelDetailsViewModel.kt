package com.puroblast.feature_hotel_details.presentation.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_hotel.HotelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelDetailsViewModel(
    private val hotelRepository: HotelRepository
) : ViewModel() {

    init {
        loadHotelDetails()
    }

    private val _state = MutableStateFlow(HotelDetailsState())
    val state = _state.asStateFlow()

    private fun loadHotelDetails() {
        viewModelScope.launch {
            val hotel = hotelRepository.getHotel()
            _state.value = _state.value.copy(hotel = hotel)
        }
    }

    class Factory @Inject constructor(
        private val hotelRepository: HotelRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == HotelDetailsViewModel::class.java)
            return HotelDetailsViewModel(hotelRepository) as T
        }
    }
}
