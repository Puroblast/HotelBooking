package com.puroblast.feature_hotel_booking.ui.hotel_booking

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.feature_hotel_booking.databinding.FragmentSuccessPaymentBinding
import com.puroblast.feature_hotel_booking.R as featureHotelBookingR

class SuccessPaymentFragment : Fragment(featureHotelBookingR.layout.fragment_success_payment) {

    private val binding by viewBinding(FragmentSuccessPaymentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBar.setNavigationOnClickListener {
            navigateToMainScreen()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigateToMainScreen()
        }

        binding.backToMainMenuButton.setOnClickListener {
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        val request =
            NavDeepLinkRequest.Builder.fromUri(
                "android-app://com.puroblast.hoteldetails/hotelDetailsFragment".toUri()
            )
                .build()

        val homeNavGraphResourceId = resources.getIdentifier(
            "main_nav_graph", "id", requireContext().packageName
        )

        val navigationOptions = NavOptions.Builder().setPopUpTo(
            destinationId = homeNavGraphResourceId, inclusive = true
        ).build()

        findNavController().navigate(request, navigationOptions)
    }
}
