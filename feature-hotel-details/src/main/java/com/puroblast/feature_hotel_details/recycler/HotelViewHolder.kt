package com.puroblast.feature_hotel_details.recycler

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.chip.Chip
import com.puroblast.common_recycler.CommonAdapter
import com.puroblast.common_recycler.CommonDelegateItem
import com.puroblast.domain_hotel.model.AboutTheHotel
import com.puroblast.domain_hotel.model.Hotel
import com.puroblast.feature_hotel_details.R
import com.puroblast.feature_hotel_details.databinding.AboutHotelItemBinding
import com.puroblast.feature_hotel_details.databinding.HotelItemBinding
import com.puroblast.feature_hotel_details.databinding.ImageItemBinding
import com.puroblast.feature_hotel_details.recycler.delegate.ImageAdapterDelegate
import com.puroblast.feature_hotel_details.recycler.model.AboutHotelItem
import com.puroblast.feature_hotel_details.recycler.model.HotelItem
import com.puroblast.feature_hotel_details.recycler.model.ImageItem

class HotelViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val imageItemBinding by viewBinding(ImageItemBinding::bind)
    private val hotelItemBinding by viewBinding(HotelItemBinding::bind)
    private val aboutHotelItemBinding by viewBinding(AboutHotelItemBinding::bind)

    private fun bindImageItem(item: ImageItem) {
        imageItemBinding.hotelImage.load(item.content())
    }

    private fun bindHotelItem(item: HotelItem) {
        with(item.content() as Hotel) {
            with(hotelItemBinding) {
                hotelAddress.text = address
                hotelName.text = name
                hotelPriceForIt.text = priceForIt
                hotelPrice.text = view.context.getString(
                    R.string.rouble_symbol,
                    minimalPrice.toString()
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
                viewPagerAdapter.submitList(images)
            }
        }
    }

    private fun bindAboutHotelItem(item: AboutHotelItem) {
        with(item.content() as AboutTheHotel) {
            with(aboutHotelItemBinding) {
                peculiarities.forEach {
                    val chip = LayoutInflater.from(view.context).inflate(
                        R.layout.chip_item, peculiaritiesChipGroup, false
                    ) as Chip
                    chip.text = it
                    peculiaritiesChipGroup.addView(chip)
                }
                descriptionText.text = description
            }
        }
    }

    fun bind(item: CommonDelegateItem) {
        when (item) {
            is ImageItem -> bindImageItem(item)
            is HotelItem -> bindHotelItem(item)
            is AboutHotelItem -> bindAboutHotelItem(item)
        }
    }
}
