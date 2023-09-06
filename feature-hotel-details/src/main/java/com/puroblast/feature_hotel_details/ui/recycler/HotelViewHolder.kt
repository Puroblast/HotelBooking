package com.puroblast.feature_hotel_details.ui.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.chip.Chip
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.AboutTheHotel
import com.puroblast.domain_hotel.model.Hotel
import com.puroblast.domain_hotel.model.Room
import com.puroblast.feature_hotel_details.R as featureHotelDetailsR
import com.puroblast.feature_hotel_details.databinding.AboutHotelItemBinding
import com.puroblast.feature_hotel_details.databinding.ChooseRoomBottomButtonItemBinding
import com.puroblast.feature_hotel_details.databinding.HotelItemBinding
import com.puroblast.feature_hotel_details.databinding.ImageItemBinding
import com.puroblast.feature_hotel_details.databinding.RoomItemBinding
import com.puroblast.feature_hotel_details.ui.recycler.delegate.ImageAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.AboutHotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.BottomItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.HotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.ImageItem
import com.puroblast.feature_hotel_details.ui.recycler.model.rooms.RoomItem
import com.puroblast.common_resources.R as commonResourcesR
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class HotelViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val imageItemBinding by viewBinding(ImageItemBinding::bind)
    private val hotelItemBinding by viewBinding(HotelItemBinding::bind)
    private val aboutHotelItemBinding by viewBinding(AboutHotelItemBinding::bind)
    private val roomItemBinding by viewBinding(RoomItemBinding::bind)
    private val bottomItemBinding by viewBinding(ChooseRoomBottomButtonItemBinding::bind)

    fun bind(item: CommonDelegateItem, args: Bundle = bundleOf()) {
        when (item) {
            is ImageItem -> bindImageItem(item)
            is HotelItem -> bindHotelItem(item)
            is BottomItem -> bindBottomItem(args)
            is AboutHotelItem -> bindAboutHotelItem(item)
            is RoomItem -> bindRoomItem(item)
        }
    }

    private fun bindImageItem(item: ImageItem) {
        imageItemBinding.hotelImage.scaleType = ImageView.ScaleType.CENTER_CROP
        imageItemBinding.hotelImage.load(item.content())
    }

    private fun bindHotelItem(item: HotelItem) {
        val hotel = item.content() as Hotel
        with(hotelItemBinding) {
            hotelAddress.text = hotel.address
            hotelName.text = hotel.name
            hotelPriceForIt.text = hotel.priceForIt

            val decimalFormatSymbols = DecimalFormatSymbols()
            decimalFormatSymbols.groupingSeparator = ' '
            val price = DecimalFormat("#,##0", decimalFormatSymbols).format(hotel.minimalPrice)
            hotelPrice.text = view.context.getString(
                commonResourcesR.string.rouble_symbol, price.toString()
            )
            hotelRatingChip.text = "${hotel.rating} ${hotel.ratingName}"

            val viewPagerAdapter = CommonAdapter().apply {
                addDelegate(ImageAdapterDelegate())
            }
            imageViewPager.adapter = viewPagerAdapter
            circleIndicator.setViewPager(imageViewPager)
            val images = mutableListOf<CommonDelegateItem>()
            for (index in hotel.imageUrls.indices) {
                images.add(ImageItem(index, hotel.imageUrls[index]))
            }
            circleIndicator.createIndicators(images.size, 0)
            viewPagerAdapter.submitList(images)
        }
    }

    private fun bindAboutHotelItem(item: AboutHotelItem) {
        val aboutTheHotel = item.content() as AboutTheHotel
        with(aboutHotelItemBinding) {
            aboutTheHotel.peculiarities.forEach {
                val chip = LayoutInflater.from(view.context).inflate(
                    featureHotelDetailsR.layout.chip_item, peculiaritiesChipGroup, false
                ) as Chip
                chip.text = it
                peculiaritiesChipGroup.addView(chip)
            }

            descriptionText.text = aboutTheHotel.description
        }
    }

    private fun bindRoomItem(item: RoomItem) {
        val room = item.content() as Room
        with(roomItemBinding) {
            val decimalFormatSymbols = DecimalFormatSymbols()
            decimalFormatSymbols.groupingSeparator = ' '
            val price = DecimalFormat("#,##0", decimalFormatSymbols).format(room.price)
            roomPriceText.text = view.context.getString(
                commonResourcesR.string.room_rouble_symbol, price.toString()
            )
            roomPriceForIt.text = room.pricePer

            val viewPagerAdapter = CommonAdapter().apply {
                addDelegate(ImageAdapterDelegate())
            }
            imageViewPager.adapter = viewPagerAdapter
            circleIndicator.setViewPager(imageViewPager)
            val images = mutableListOf<CommonDelegateItem>()
            for (index in room.imageUrls.indices) {
                images.add(ImageItem(index, room.imageUrls[index]))
            }
            circleIndicator.createIndicators(images.size, 0)
            viewPagerAdapter.submitList(images)

            room.peculiarities.forEach {
                val chip = LayoutInflater.from(view.context).inflate(
                    featureHotelDetailsR.layout.chip_item, peculiaritiesChipGroup, false
                ) as Chip
                chip.text = it
                peculiaritiesChipGroup.addView(chip)
            }

            chooseRoomButton.setOnClickListener {
                // TODO: NAVIGATE TO NEXT FRAG
            }
            roomName.text = room.name
        }
    }

    private fun bindBottomItem(args: Bundle) {
        bottomItemBinding.chooseRoomButton.setOnClickListener {
            view.findNavController().navigate(
                featureHotelDetailsR.id.action_hotelDetailsFragment_to_roomsFragment, args
            )
        }
    }

}
