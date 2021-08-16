package com.zythem.roombooker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zythem.roombooker.data.model.Room

@Dao
interface RoomsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(room: Room)

    @Query("SELECT * FROM room")
    suspend fun load(): List<Room>
}
