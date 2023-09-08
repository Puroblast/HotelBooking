package com.puroblast.feature_hotel_booking.ui.recycler

import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.transition.AutoTransition
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.BookingDetails
import com.puroblast.domain_hotel.model.Tourist
import com.puroblast.feature_hotel_booking.databinding.AddTouristItemBinding
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR
import com.puroblast.feature_hotel_booking.databinding.BookBottomButtonItemBinding
import com.puroblast.feature_hotel_booking.databinding.BookingInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.BuyerInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.HotelInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.TourPaymentInfoItemBinding
import com.puroblast.feature_hotel_booking.databinding.TouristInfoItemBinding
import com.puroblast.feature_hotel_booking.presentation.ClickListener
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookingInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.HotelInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TourPaymentInfoItem
import com.puroblast.feature_hotel_booking.ui.recycler.model.TouristInfoItem
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
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
    private val addTouristItemBinding by viewBinding(AddTouristItemBinding::bind)

    fun bind(item: CommonDelegateItem) {
        when (item) {
            is BookingInfoItem -> bindBookingInfoItem(item)
            is HotelInfoItem -> bindHotelInfoItem(item)
            is BuyerInfoItem -> bindBuyerInfoItem()
            is TouristInfoItem -> bindTouristInfoItem(item)
            is TourPaymentInfoItem -> bindTourPaymentInfoItem(item)
        }
    }

    fun bindBottomItem(item: CommonDelegateItem , onClick: ClickListener) {
        val buttonText = item.content() as String
        bottomItemBinding.bookButton.text = buttonText
        bottomItemBinding.bookButton.setOnClickListener {
            onClick.onPayButtonClick()
        }
    }

    fun bindAddTouristItem(onClick: ClickListener) {
        addTouristItemBinding.addButton.setOnClickListener {
            onClick.onAddTouristButtonClick()
        }
    }

    private fun bindBuyerInfoItem() {
        val phoneMask = MaskImpl(PredefinedSlots.RUS_PHONE_NUMBER, true).apply {
            isShowingEmptySlots = true
            placeholder = '*'
            isHideHardcodedHead = true
        }
        val watcher = MaskFormatWatcher(phoneMask)
        watcher.installOn(buyerInfoBinding.phoneNumberText)

        buyerInfoBinding.phoneNumberText.setOnFocusChangeListener { view, isFocused ->
            if (isFocused) {
                buyerInfoBinding.phoneNumberInput.placeholderText = phoneMask.toString()
                buyerInfoBinding.phoneNumberText.hint = ""
            } else {
                if (!watcher.mask.hasUserInput()) {
                    buyerInfoBinding.phoneNumberText.setHint(featureHotelBookingR.string.input_phone_number)
                }
            }
        }
    }

    private fun bindTourPaymentInfoItem(item: CommonDelegateItem) {
        val bookingDetails = item.content() as BookingDetails
        with(tourPaymentBinding) {
            val decimalFormatSymbols = DecimalFormatSymbols()
            decimalFormatSymbols.groupingSeparator = ' '
            val format = DecimalFormat("#,##0", decimalFormatSymbols)

            val overallPrice = bookingDetails.tourPrice + bookingDetails.fuelCharge + bookingDetails.serviceCharge
            tourPaymentText.text = view.context.getString(
                commonResourcesR.string.room_rouble_symbol ,
                format.format(bookingDetails.tourPrice)
            )
            fuelChargeText.text = view.context.getString(
                commonResourcesR.string.room_rouble_symbol ,
                format.format(bookingDetails.fuelCharge)
            )
            serviceChargeText.text = view.context.getString(
                commonResourcesR.string.room_rouble_symbol ,
                format.format(bookingDetails.serviceCharge)
            )
            overallPaymentText.text = view.context.getString(
                commonResourcesR.string.room_rouble_symbol ,
                format.format(overallPrice)
            )
        }
    }

    private fun bindTouristInfoItem(item: CommonDelegateItem) {
        val touristPosition = TouristPositionMapper()
        val tourist = item.content() as Tourist
        with(touristInfoBinding) {
            touristNumber.text = touristPosition.map(tourist.id)

            addButton.setOnClickListener {
                if (hiddenView.isVisible) {
                    TransitionManager.beginDelayedTransition(touristCardView, AutoTransition())
                    addButton.setImageResource(commonResourcesR.drawable.collapsed_arrow)
                    hiddenView.isVisible = false
                } else {
                    TransitionManager.beginDelayedTransition(touristCardView, AutoTransition())
                    addButton.setImageResource(commonResourcesR.drawable.expanded_arrow)
                    hiddenView.isVisible = true
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

}
