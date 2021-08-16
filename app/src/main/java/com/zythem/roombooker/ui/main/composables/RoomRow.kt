package com.zythem.roombooker.ui.main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import com.zythem.roombooker.R
import com.zythem.roombooker.data.model.Room
import com.zythem.roombooker.ui.theme.RoomBookerTheme

@Composable
fun RoomRow(
    room: Room,
    onButtonTapped: (String) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val width = maxWidth

        val spinner = CircularProgressDrawable(LocalContext.current)
        spinner.setStyle(CircularProgressDrawable.LARGE)
        spinner.start()

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberImagePainter(
                            data = room.thumbnail,
                            builder = {
                                crossfade(true)
                                placeholder(spinner)
                                error(spinner)
                            }
                        ),
                        contentDescription = "Room Image",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        modifier = Modifier.size(
                            width = width,
                            height = width * 2 / 3
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = dimensionResource(id = R.dimen.padding)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = room.name,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        if (room.thumbnail.isNotBlank()) Text(
                            text = LocalContext.current.resources.getQuantityString(
                                R.plurals.spots_remaining,
                                room.spots,
                                room.spots
                            ) ,
                            textAlign = TextAlign.Center
                        )
                    }
                    if (room.thumbnail.isNotBlank()) Button(
                        enabled = room.spots > 0,
                        onClick = { onButtonTapped(room.name) }) {
                        Text(text = stringResource(id = R.string.button_title))
                    }
                }
            }
        }
    }
}

@Preview(name = "Room Row (smallest screen)", showBackground = true, widthDp = 320)
@Preview(name = "Room Row (large screen)", showBackground = true, device = Devices.PIXEL_C)
@Composable
fun RoomRowPreview() {
    RoomBookerTheme {
        RoomRow(room = Room(), onButtonTapped = {})
    }
}