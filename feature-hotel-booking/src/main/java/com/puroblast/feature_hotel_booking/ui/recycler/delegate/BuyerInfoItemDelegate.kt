package com.puroblast.feature_hotel_booking.ui.recycler.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puroblast.common_recycler.CommonAdapterDelegate
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR
import com.puroblast.feature_hotel_booking.ui.recycler.HotelBookViewHolder
import com.puroblast.feature_hotel_booking.ui.recycler.model.BuyerInfoItem

class BuyerInfoItemDelegate : CommonAdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            featureHotelBookingR.layout.buyer_info_item,
            parent,
            false
        )
        return HotelBookViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: CommonDelegateItem,
        position: Int
    ) {
        (holder as HotelBookViewHolder).bind(item)
    }

    override fun isOfViewType(item: CommonDelegateItem): Boolean {
        return item is BuyerInfoItem
    }
}
