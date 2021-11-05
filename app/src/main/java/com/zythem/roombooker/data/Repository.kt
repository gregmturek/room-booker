package com.zythem.roombooker.data

import android.util.Log
import com.zythem.roombooker.data.database.RoomsDao
import com.zythem.roombooker.data.model.Room
import com.zythem.roombooker.data.network.WebService
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val webService: WebService,
    private val roomsDao: RoomsDao
) {
    suspend fun getRooms(): List<Room> {
        val rooms = try {
            val rooms = webService.getRooms().list
            saveRooms(rooms)
            rooms
        } catch (e: IOException) {
            Log.e(TAG, "IOException: $e")
            roomsDao.load()
        }
        return if (rooms.isNullOrEmpty()) listOf(Room()) else rooms
    }

    suspend fun bookRoom(name: String): Boolean {
        return try {
            webService.bookRoom(name).success
        } catch (e: IOException) {
            Log.e(TAG, "IOException: $e")
            false
        }
    }

    private suspend fun saveRooms(rooms: List<Room>) {
        roomsDao.save(rooms)

    }

    companion object {
        private val TAG = Repository::class.java.simpleName
    }
}
