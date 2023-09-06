package com.puroblast.feature_hotel_booking.ui.recycler

import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.transition.AutoTransition
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.feature_hotel_booking.databinding.BookBottomButtonItemBinding
import com.puroblast.feature_hotel_booking.databinding.BookingInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.BuyerInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.HotelInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.TourPaymentInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.TouristInfoItemBinding
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookHotelBottomItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookingInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.HotelInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TourPaymentInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TouristInfoItem
import com.puroblast.common_resources.R as commonResourcesR

class HotelBookViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val tourPaymentBinding by viewBinding(TourPaymentInfoItemBinding::bind)
    private val touristInfoBinding by viewBinding(TouristInfoItemBinding::bind)
    private val hotelInfoBinding by viewBinding(HotelInfoItemBinding::bind)
    private val buyerInfoBinding by viewBinding(BuyerInfoItemBinding::bind)
    private val bookingInfoBinding by viewBinding(BookingInfoItemBinding::bind)
    private val bottomItemBinding by viewBinding(BookBottomButtonItemBinding::bind)

    fun bind(item: CommonDelegateItem) {
        when (item) {
            is BookHotelBottomItem -> bindBottomItem(item)
            is BookingInfoItem -> bindBookingInfoItem(item)
            is BuyerInfoItem -> bindBuyerInfoItem()
            is HotelInfoItem -> bindHotelInfoItem(item)
            is TouristInfoItem -> bindTouristInfoItem()
            is TourPaymentInfoItem -> bindTourPaymentInfoItem(item)
        }
    }

    private fun bindTourPaymentInfoItem(item: CommonDelegateItem) {
        val bookingDetails = item.content() as BookingDetails
        with(tourPaymentBinding) {
            val tourPrice = bookingDetails.tourPrice
            val fuelCharge = bookingDetails.fuelCharge
            val serviceCharge = bookingDetails.serviceCharge
            val overallPrice = tourPrice + fuelCharge + serviceCharge
            tourPaymentText.text = tourPrice.toString()
            fuelChargeText.text = fuelCharge.toString()
            serviceChargeText.text = serviceCharge.toString()
            overallPaymentText.text = overallPrice.toString()
        }
    }

    private fun bindTouristInfoItem() {
        with(touristInfoBinding) {
            addButton.setOnClickListener {
                if (hiddenView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(touristCardView, AutoTransition())
                    hiddenView.visibility = View.GONE
                    addButton.setImageResource(commonResourcesR.drawable.collapsed_arrow)
                } else {
                    TransitionManager.beginDelayedTransition(touristCardView, AutoTransition())
                    hiddenView.visibility = View.VISIBLE
                    addButton.setImageResource(commonResourcesR.drawable.expanded_arrow)
                }
            }
        }
    }

    private fun bindHotelInfoItem(item: CommonDelegateItem) {
        val bookingDetails = item.content() as BookingDetails
        with(hotelInfoBinding) {
            hotelRatingChip.text = "${bookingDetails.hotelRating} ${bookingDetails.ratingName}"
            hotelName.text = bookingDetails.hotelName
            hotelAddress.text = bookingDetails.hotelAddress
        }
    }

    private fun bindBuyerInfoItem() {
    }

    private fun bindBookingInfoItem(item: CommonDelegateItem) {
        val bookingDetails = item.content() as BookingDetails
        with(bookingInfoBinding) {
            departureFromText.text = bookingDetails.departure
            countryAndCityText.text = bookingDetails.arrivalCountry
            datesText.text = "${bookingDetails.tourDateStart}-${bookingDetails.tourDateStop}"
            nightsAmountText.text = bookingDetails.numberOfNights.toString()
            hotelNameText.text = bookingDetails.hotelName
            roomNameText.text = bookingDetails.room
            foodTypeText.text = bookingDetails.nutrition
        }
    }

    private fun bindBottomItem(item: CommonDelegateItem) {
        val buttonText = item.content() as String
        bottomItemBinding.bookButton.text = buttonText
    }
}
