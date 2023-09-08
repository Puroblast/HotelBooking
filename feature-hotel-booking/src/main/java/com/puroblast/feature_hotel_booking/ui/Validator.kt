package com.puroblast.feature_hotel_booking.ui

class Validator {

    private val fields = mutableListOf<ValidationField>()

    fun validateFields(): Boolean {
        val result = fields.map { field ->
                field.validate()
        }.filter { !it }

        return result.isEmpty()
    }

    fun addField(field: ValidationField) {
        fields.add(field)
    }
}
