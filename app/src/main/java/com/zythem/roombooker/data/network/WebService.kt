package com.zythem.roombooker.data.network

import com.zythem.roombooker.data.model.BookingResult
import com.zythem.roombooker.data.model.Rooms
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("rooms.json")
    suspend fun getRooms(): Rooms

    @GET("bookRoom.json")
    suspend fun bookRoom(@Query("name") name: String): BookingResult
}
