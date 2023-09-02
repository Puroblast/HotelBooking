package com.puroblast.feature_hotel_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.feature_hotel_details.databinding.FragmentHotelBinding
import com.puroblast.feature_hotel_details.recycler.delegate.AboutHotelAdapterDelegate
import com.puroblast.feature_hotel_details.recycler.delegate.HotelAdapterDelegate

class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private val binding by viewBinding(FragmentHotelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotelAdapter = CommonAdapter().apply {
            addDelegate(HotelAdapterDelegate())
            addDelegate(AboutHotelAdapterDelegate())
        }
    }
}
