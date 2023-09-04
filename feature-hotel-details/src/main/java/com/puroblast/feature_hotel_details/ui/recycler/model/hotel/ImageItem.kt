package com.puroblast.feature_hotel_details.ui.recycler.model.hotel

import com.puroblast.common_recycler.CommonDelegateItem

class ImageItem(
    private val id: Int,
    private val value: String
) : CommonDelegateItem {

    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as ImageItem).value == content()
    }
}
