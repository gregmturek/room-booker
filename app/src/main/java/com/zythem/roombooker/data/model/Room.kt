package com.zythem.roombooker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Room(
    @PrimaryKey val name: String = "Loading...",
    val spots: Int = 0,
    val thumbnail: String = "",
)
