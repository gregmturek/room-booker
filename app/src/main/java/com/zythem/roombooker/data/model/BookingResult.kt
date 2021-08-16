package com.zythem.roombooker.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookingResult (
    val success: Boolean = false
)
