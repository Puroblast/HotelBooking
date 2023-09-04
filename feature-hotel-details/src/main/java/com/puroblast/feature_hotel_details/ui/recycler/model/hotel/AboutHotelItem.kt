package com.puroblast.feature_hotel_details.ui.recycler.model.hotel
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.AboutTheHotel

class AboutHotelItem(
    private val id: Int = 0,
    private val value: AboutTheHotel
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as AboutHotelItem).value == content()
    }
}
