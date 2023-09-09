package com.puroblast.feature_hotel_booking.ui

import android.text.TextUtils
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText
import com.puroblast.common_resources.R as commonResourcesR

fun TextInputEditText.validateEmpty(): Boolean {
    val text = this.text.toString()
    val checkResult = !TextUtils.isEmpty(text)
    return checkResult.also {
        if (!it) {
            this.setHintTextColor(resources.getColor(commonResourcesR.color.error_color))
        }
    }
}

fun TextInputEditText.validateEmail(): Boolean {
    val text = this.text.toString()
    val result = !TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches()
    return result.also {
        if (!it) {
            this.setHintTextColor(resources.getColor(commonResourcesR.color.error_color))
            this.setTextColor(resources.getColor(commonResourcesR.color.error_color))
        }
    }
}

fun TextInputEditText.validatePhoneNumber(): Boolean {
    val text = this.text.toString().filter {
        !(it == '*' || it == '-' || it == '(' || it == ')' || it == '+' || it == ' ')
    }
    val result = TextUtils.isEmpty(text)
    if (result) {
        this.setHintTextColor(resources.getColor(commonResourcesR.color.error_color))
        return false
    } else {
        if (text.length != 11) {
            this.setTextColor(resources.getColor(commonResourcesR.color.error_color))
            return false
        }
    }

    return true
}
