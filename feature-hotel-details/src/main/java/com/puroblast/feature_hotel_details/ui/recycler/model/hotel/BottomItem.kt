package com.puroblast.feature_hotel_details.ui.recycler.model.hotel

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.Hotel

class BottomItem(
    val value: Hotel
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = 0

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as BottomItem).value == content()
    }
}
