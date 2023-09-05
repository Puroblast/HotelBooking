package com.puroblast.feature_hotel_details.ui.room_details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.feature_hotel_details.databinding.FragmentRoomsBinding
import com.puroblast.feature_hotel_details.di.HotelDetailsComponentViewModel
import com.puroblast.feature_hotel_details.presentation.rooms.RoomsDetailsViewModel
import com.puroblast.feature_hotel_details.ui.recycler.delegate.rooms.RoomAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.model.rooms.RoomItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR

class RoomsFragment : Fragment(featureHotelDetailsR.layout.fragment_rooms) {

    private val binding by viewBinding(FragmentRoomsBinding::bind)

    @Inject
    internal lateinit var roomsDetailsViewModelFactory: Lazy<RoomsDetailsViewModel.Factory>
    private val viewModel: RoomsDetailsViewModel by viewModels {
        roomsDetailsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<HotelDetailsComponentViewModel>()
            .hotelDetailsComponent.inject(this)

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roomAdapter = setupAdapter()
        render(roomAdapter)

        binding.roomToolBar.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.roomToolBar.title = requireArguments().getString("hotelName")
    }

    private fun setupAdapter(): CommonAdapter {
        val roomAdapter = CommonAdapter().apply {
            addDelegate(RoomAdapterDelegate())
        }

        binding.recycler.adapter = roomAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        return roomAdapter
    }

    private fun render(adapter: CommonAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { state ->
                    val roomItems = mutableListOf<RoomItem>()
                    state.rooms.rooms.forEach { room ->
                        val roomItem = RoomItem(room)
                        roomItems.add(roomItem)
                    }
                    adapter.submitList(roomItems.toList())
                }
            }
        }
    }
}
