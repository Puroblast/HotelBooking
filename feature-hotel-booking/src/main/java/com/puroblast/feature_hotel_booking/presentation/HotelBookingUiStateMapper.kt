package com.puroblast.feature_hotel_booking.presentation

import android.content.Context
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.Tourist
import com.puroblast.feature_hotel_booking.ui.recycler.model.AddTouristItem
import com.puroblast.common_resources.R as commonResourcesR
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookHotelBottomItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookingInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.HotelInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TourPaymentInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TouristInfoItem
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class HotelBookingUiStateMapper() {

    fun map(context: Context, state: HotelBookingState): HotelBookingUiState {
        if (state.bookingDetails == null) {
            return HotelBookingUiState(emptyList())
        }

        with(state.bookingDetails) {
            val decimalFormatSymbols = DecimalFormatSymbols()
            decimalFormatSymbols.groupingSeparator = ' '
            val overallPay = fuelCharge + serviceCharge + tourPrice
            val price = DecimalFormat("#,##0", decimalFormatSymbols).format(overallPay)
            val bottomButtonText = context.getString(
                commonResourcesR.string.room_rouble_symbol,
                "Оплатить $price"
            )

            val bookingInfoItem = listOf(BookingInfoItem(this))
            val buyerInfoItem = listOf(BuyerInfoItem())
            val hotelInfoItem = listOf(HotelInfoItem(this))
            val tourPaymentInfoItem = listOf(TourPaymentInfoItem(this))
            val bottomItem = listOf(BookHotelBottomItem(bottomButtonText))

            val items = buildList {
                addAll(hotelInfoItem)
                addAll(bookingInfoItem)
                addAll(buyerInfoItem)
                addAll(touristItems(state.tourists))
                addAll(tourPaymentInfoItem)
                addAll(bottomItem)
            }

            return HotelBookingUiState(items)
        }
    }

    private fun touristItems(tourists: List<Tourist>): List<CommonDelegateItem> {
        return tourists.map {
            TouristInfoItem(it)
        } + listOf(AddTouristItem())
    }
}
