package com.puroblast.feature_hotel_details.ui.recycler.delegate.hotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puroblast.common_recycler.CommonAdapterDelegate
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR
import com.puroblast.feature_hotel_details.ui.recycler.HotelViewHolder
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.BottomItem

class BottomItemAdapterDelegate : CommonAdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            featureHotelDetailsR.layout.choose_room_bottom_button_item,
            parent,
            false
        )
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: CommonDelegateItem,
        position: Int
    ) {
        (holder as HotelViewHolder).bind(item)
    }

    override fun isOfViewType(item: CommonDelegateItem): Boolean {
        return item is BottomItem
    }
}
