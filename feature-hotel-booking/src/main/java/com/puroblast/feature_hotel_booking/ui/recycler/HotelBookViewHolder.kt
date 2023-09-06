package com.puroblast.feature_hotel_booking.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookHotelBottomItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookingInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.HotelInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TourPaymentInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TouristInfoItem

class HotelBookViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(item : CommonDelegateItem) {
        when(item) {
            is BookHotelBottomItem -> bindBottomItem()
            is BookingInfoItem -> bindBookingInfoItem()
            is BuyerInfoItem -> bindBuyerInfoItem()
            is HotelInfoItem -> bindHotelInfoItem()
            is TouristInfoItem -> bindTouristInfoItem()
            is TourPaymentInfoItem -> bindTourPaymentInfoItem()
        }
    }

    private fun bindTourPaymentInfoItem() {
        TODO("Not yet implemented")
    }

    private fun bindTouristInfoItem() {
        TODO("Not yet implemented")
    }

    private fun bindHotelInfoItem() {
        TODO("Not yet implemented")
    }

    private fun bindBuyerInfoItem() {
        TODO("Not yet implemented")
    }

    private fun bindBookingInfoItem() {
        TODO("Not yet implemented")
    }

    private fun bindBottomItem() {
        TODO("Not yet implemented")
    }
}
