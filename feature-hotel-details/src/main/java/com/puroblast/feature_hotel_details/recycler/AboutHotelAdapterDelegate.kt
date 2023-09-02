package com.puroblast.feature_hotel_details.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puroblast.common_recycler.CommonAdapterDelegate
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.feature_hotel_details.recycler.model.AboutHotelItem
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR

class AboutHotelAdapterDelegate: CommonAdapterDelegate{

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            featureHotelDetailsR.layout.about_hotel_item,
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
        return item is AboutHotelItem
    }
}
