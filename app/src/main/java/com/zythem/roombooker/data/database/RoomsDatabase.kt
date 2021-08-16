package com.zythem.roombooker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zythem.roombooker.data.model.Room

@Database(entities = [Room::class], version = 1)
abstract class RoomsDatabase: RoomDatabase() {
    abstract fun getRoomsDao(): RoomsDao
}
