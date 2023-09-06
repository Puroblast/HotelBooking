package com.puroblast.feature_hotel_booking.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_hotel.HotelRepository
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
                id = bookingDetails.id,
                hotelName = bookingDetails.hotelName,
                hotelAddress = bookingDetails.hotelAddress,
                hotelRating = bookingDetails.hotelRating,
                ratingName = bookingDetails.ratingName,
                departure = bookingDetails.departure,
                arrivalCountry = bookingDetails.arrivalCountry,
                tourDateStart = bookingDetails.tourDateStart,
                tourDateStop = bookingDetails.tourDateStop,
                numberOfNights = bookingDetails.numberOfNights,
                room = bookingDetails.room,
                nutrition = bookingDetails.nutrition,
                tourPrice = bookingDetails.tourPrice,
                fuelCharge = bookingDetails.fuelCharge,
                serviceCharge = bookingDetails.serviceCharge,
                tourists = listOf()
            )
        }
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
