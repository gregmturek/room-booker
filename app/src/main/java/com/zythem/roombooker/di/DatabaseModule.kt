package com.zythem.roombooker.di

import android.content.Context
import androidx.room.Room
import com.zythem.roombooker.data.database.RoomsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RoomsDatabase::class.java,
        "rooms_database"
    ).build()

    @Provides
    @Singleton
    fun provideRoomsDao(database: RoomsDatabase) = database.getRoomsDao()
}