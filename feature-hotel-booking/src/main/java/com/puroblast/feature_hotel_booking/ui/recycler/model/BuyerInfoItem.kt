package com.puroblast.feature_hotel_booking.ui.recycler.model

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class BuyerInfoItem(
    private val value: Int = featureHotelBookingR.layout.buyer_info_item
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = 0

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as BuyerInfoItem).value == content()
    }
}
