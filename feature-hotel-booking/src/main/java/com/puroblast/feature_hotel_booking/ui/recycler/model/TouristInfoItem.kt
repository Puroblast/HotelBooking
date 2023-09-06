package com.puroblast.feature_hotel_booking.ui.recycler.model

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.Tourist
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class TouristInfoItem(
    private val value: Tourist
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = 0

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as TouristInfoItem).value == content()
    }
}
