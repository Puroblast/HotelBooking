package com.puroblast.feature_hotel_booking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_hotel.HotelRepository
import com.puroblast.domain_hotel.model.Tourist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelBookingViewModel(
    private val hotelRepository: HotelRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HotelBookingState())
    val state = _state.asStateFlow()

    init {
        loadBookingDetails()
    }

    private fun loadBookingDetails() {
        viewModelScope.launch {
            val bookingDetails = hotelRepository.getBookingDetails()
            _state.value = _state.value.copy(
                bookingDetails = bookingDetails,
                tourists = listOf(Tourist(1))
            )
        }
    }

    fun addTourist() {
        _state.value = _state.value.copy(
            tourists = _state.value.tourists + Tourist(id = _state.value.tourists.size + 1)
        )
    }

    class Factory @Inject constructor(
        private val hotelRepository: HotelRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == HotelBookingViewModel::class.java)
            return HotelBookingViewModel(hotelRepository) as T
        }
    }
}
