package com.puroblast.feature_hotel_details.ui.recycler.model.hotel

import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_details.R

class BottomItem(
    val value: Int = R.layout.bottom_button_item
) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = 0

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as BottomItem).value == content()
    }
}
