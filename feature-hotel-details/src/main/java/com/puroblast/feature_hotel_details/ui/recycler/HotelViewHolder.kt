package com.puroblast.feature_hotel_details.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
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
import com.puroblast.feature_hotel_details.databinding.HotelItemBinding
import com.puroblast.feature_hotel_details.databinding.ImageItemBinding
import com.puroblast.feature_hotel_details.databinding.RoomItemBinding
import com.puroblast.feature_hotel_details.ui.recycler.delegate.ImageAdapterDelegate
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.AboutHotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.HotelItem
import com.puroblast.feature_hotel_details.ui.recycler.model.hotel.ImageItem
import com.puroblast.feature_hotel_details.ui.recycler.model.rooms.RoomItem
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class HotelViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val imageItemBinding by viewBinding(ImageItemBinding::bind)
    private val hotelItemBinding by viewBinding(HotelItemBinding::bind)
    private val aboutHotelItemBinding by viewBinding(AboutHotelItemBinding::bind)
    private val roomItemBinding by viewBinding(RoomItemBinding::bind)

    private fun bindImageItem(item: ImageItem) {
        imageItemBinding.hotelImage.scaleType = ImageView.ScaleType.CENTER_CROP
        imageItemBinding.hotelImage.load(item.content())
    }

    private fun bindHotelItem(item: HotelItem) {
        with(item.content() as Hotel) {
            with(hotelItemBinding) {
                hotelAddress.text = address
                hotelName.text = name
                hotelPriceForIt.text = priceForIt
                val decimalFormatSymbols = DecimalFormatSymbols()
                decimalFormatSymbols.groupingSeparator = ' '
                val price = DecimalFormat("#,##0", decimalFormatSymbols).format(minimalPrice)
                hotelPrice.text = view.context.getString(
                    featureHotelDetailsR.string.rouble_symbol, price.toString()
                )
                hotelRatingChip.text = "$rating $ratingName"

                val viewPagerAdapter = CommonAdapter().apply {
                    addDelegate(ImageAdapterDelegate())
                }
                imageViewPager.adapter = viewPagerAdapter
                circleIndicator.setViewPager(imageViewPager)
                val images = mutableListOf<CommonDelegateItem>()
                for (index in imageUrls.indices) {
                    images.add(ImageItem(index, imageUrls[index]))
                }
                circleIndicator.createIndicators(images.size, 0)
                viewPagerAdapter.submitList(images)
            }
        }
    }

    private fun bindAboutHotelItem(item: AboutHotelItem) {
        with(item.content() as AboutTheHotel) {
            with(aboutHotelItemBinding) {
                peculiarities.forEach {
                    val chip = LayoutInflater.from(view.context).inflate(
                        featureHotelDetailsR.layout.chip_item, peculiaritiesChipGroup, false
                    ) as Chip
                    chip.text = it
                    peculiaritiesChipGroup.addView(chip)
                }
                descriptionText.text = description
            }
        }
    }

    private fun bindRoomItem(item: RoomItem) {
        with(item.content() as Room) {
            with(roomItemBinding) {
                roomName.text = name

                val decimalFormatSymbols = DecimalFormatSymbols()
                decimalFormatSymbols.groupingSeparator = ' '
                val price = DecimalFormat("#,##0", decimalFormatSymbols).format(price)

                roomPriceText.text = view.context.getString(
                    featureHotelDetailsR.string.rouble_symbol, price.toString()
                )

                roomPriceForIt.text = pricePer
                val viewPagerAdapter = CommonAdapter().apply {
                    addDelegate(ImageAdapterDelegate())
                }

                imageViewPager.adapter = viewPagerAdapter
                circleIndicator.setViewPager(imageViewPager)
                val images = mutableListOf<CommonDelegateItem>()
                for (index in imageUrls.indices) {
                    images.add(ImageItem(index, imageUrls[index]))
                }
                circleIndicator.createIndicators(images.size, 0)
                viewPagerAdapter.submitList(images)
            }
        }
    }

    fun bind(item: CommonDelegateItem) {
        when (item) {
            is ImageItem -> bindImageItem(item)
            is HotelItem -> bindHotelItem(item)
            is AboutHotelItem -> bindAboutHotelItem(item)
            is RoomItem -> bindRoomItem(item)
        }
    }
}
