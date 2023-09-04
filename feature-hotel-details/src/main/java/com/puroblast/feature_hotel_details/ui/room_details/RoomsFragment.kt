package com.puroblast.feature_hotel_details.ui.room_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.feature_hotel_details.databinding.FragmentRoomsBinding
import com.puroblast.feature_hotel_details.ui.recycler.delegate.rooms.RoomAdapterDelegate
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR

class RoomsFragment : Fragment(featureHotelDetailsR.layout.fragment_rooms) {

    private val binding by viewBinding(FragmentRoomsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.roomToolBar.title = requireArguments().getString("hotelName")
        val hotelAdapter = setupAdapter()
        binding.recycler.adapter = hotelAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun setupAdapter(): CommonAdapter {
        val hotelAdapter = CommonAdapter().apply {
            addDelegate(RoomAdapterDelegate())
        }
        return hotelAdapter
    }
}
