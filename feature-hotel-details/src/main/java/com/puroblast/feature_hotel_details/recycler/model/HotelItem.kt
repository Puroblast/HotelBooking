package com.puroblast.feature_hotel_details.recycler.model

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.Hotel

class HotelItem(
    private val value : Hotel
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = value.id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as HotelItem).value == content()
    }
}
