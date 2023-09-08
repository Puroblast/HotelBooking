package com.puroblast.feature_hotel_booking.ui.recycler.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puroblast.common_recycler.CommonAdapterDelegate
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_booking.presentation.ClickListener
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR
import com.puroblast.feature_hotel_booking.ui.recycler.HotelBookViewHolder
import com.puroblast.feature_hotel_booking.ui.recycler.model.BookHotelBottomItem

class BottomAdapterDelegate(
    private val onClick: ClickListener
) : CommonAdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            featureHotelBookingR.layout.book_bottom_button_item,
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
        (holder as HotelBookViewHolder).bindBottomItem(item , onClick)
    }

    override fun isOfViewType(item: CommonDelegateItem): Boolean {
        return item is BookHotelBottomItem
    }

}
