package com.puroblast.feature_hotel_booking.ui.recycler.model

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class AddTouristItem(
    private val value: Int = featureHotelBookingR.layout.add_tourist_item
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = 0

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as AddTouristItem).value == content()
    }
}
