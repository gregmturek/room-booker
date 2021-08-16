package com.zythem.roombooker.ui.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zythem.roombooker.R
import com.zythem.roombooker.data.model.Room
import com.zythem.roombooker.ui.theme.RoomBookerTheme

@Composable
fun RoomList(toolbarHeight: Dp, rooms: List<Room>, onButtonTapped: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = toolbarHeight + dimensionResource(id = R.dimen.padding),
            bottom = dimensionResource(id = R.dimen.padding),
            start = dimensionResource(id = R.dimen.padding),
            end = dimensionResource(id = R.dimen.padding)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding))
    ) {
        items(rooms) { room ->
            RoomRow(room, onButtonTapped)
        }
    }
}

@Preview(name = "Room List (smallest screen)", showBackground = true, widthDp = 320)
@Preview(name = "Room List (large screen)", showBackground = true, device = Devices.PIXEL_C)
@Composable
fun RoomListPreview() {
    RoomBookerTheme() {
        RoomList(0.dp, listOf(Room(), Room(), Room())) {}
    }
}

