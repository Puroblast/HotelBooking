package com.puroblast.feature_hotel_booking.ui

import android.content.Context
import android.util.Log
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.Tourist
import com.puroblast.common_resources.R as commonResourcesR
import com.puroblast.feature_hotel_booking.presentation.HotelBookingState
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookHotelBottomItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookingInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.HotelInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TourPaymentInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TouristInfoItem
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class HotelBookingUiStateMapper {

    fun map(context: Context, state: HotelBookingState): HotelBookingUiState {

        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = ' '
        val overallPay = state.fuelCharge + state.serviceCharge + state.tourPrice
        val price = DecimalFormat("#,##0", decimalFormatSymbols).format(overallPay)
        val bottomButtonText =
            context.getString(commonResourcesR.string.room_rouble_symbol, "Оплатить $price")

        val bookingInfoItem = listOf(BookingInfoItem(state))
        val buyerInfoItem = listOf(BuyerInfoItem())
        val hotelInfoItem = listOf(HotelInfoItem(state))
        val tourPaymentInfoItem = listOf(TourPaymentInfoItem(state))
        val bottomItem = listOf(BookHotelBottomItem(bottomButtonText))

        return HotelBookingUiState(
                hotelInfoItem +
                    bookingInfoItem +
                    buyerInfoItem +
                    touristItems(state.tourists) +
                    tourPaymentInfoItem +
                    bottomItem
        )

    }

    private fun touristItems(tourists: List<Tourist>): List<CommonDelegateItem> {
        return listOf(TouristInfoItem(Tourist())) + tourists.map {
            TouristInfoItem(it)
        }
    }
}
