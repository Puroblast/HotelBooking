package com.puroblast.feature_hotel_details.ui.recycler.model.rooms

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.Room

class RoomItem(
    val value : Room
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = value.id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as RoomItem).value == content()
    }
}
