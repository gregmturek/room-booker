package com.zythem.roombooker.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rooms(
    @Json(name = "rooms")
    val list: List<Room> = listOf(Room())
)
