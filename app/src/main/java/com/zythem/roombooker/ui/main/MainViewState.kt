package com.zythem.roombooker.ui.main

import com.zythem.roombooker.data.model.Room

data class MainViewState(
    val rooms: List<Room> = listOf(Room()),
    val isRefreshing: Boolean = false
)
