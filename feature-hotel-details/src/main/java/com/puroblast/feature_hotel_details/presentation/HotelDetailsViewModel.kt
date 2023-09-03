package com.puroblast.feature_hotel_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_hotel.HotelRepository
import com.puroblast.domain_hotel.model.Hotel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelDetailsViewModel(
    private val hotelRepository: HotelRepository
) : ViewModel() {

    init {
        loadHotelDetails()
    }

    private val _state = MutableStateFlow(HotelDetailsState(Hotel()))
    val state = _state.asStateFlow()

    private fun loadHotelDetails() {
        viewModelScope.launch {
            hotelRepository.getHotel()
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
