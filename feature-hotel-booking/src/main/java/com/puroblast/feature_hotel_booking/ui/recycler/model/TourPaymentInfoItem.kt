package com.puroblast.feature_hotel_booking.ui.recycler.model

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.BookingDetails

class TourPaymentInfoItem(
    private val value: BookingDetails
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = value.id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as TourPaymentInfoItem).value == content()
    }
}
