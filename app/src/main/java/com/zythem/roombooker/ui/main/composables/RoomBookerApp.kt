package com.zythem.roombooker.ui.main.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.zythem.roombooker.R
import com.zythem.roombooker.ui.main.MainColdFlowEvent
import com.zythem.roombooker.ui.main.MainUIAction
import com.zythem.roombooker.ui.main.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

@Composable
fun RoomBookerApp(viewModel: MainViewModel) {
    val toolbarHeight = 48.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = consumed.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val viewState = viewModel.viewState.collectAsState()
    val onButtonTapped = { name: String -> viewModel.onAction(MainUIAction.ButtonTapped(name)) }
    val onSwipeRefreshed = { viewModel.onAction(MainUIAction.SwipeRefreshed) }
    val scaffoldState = rememberScaffoldState()
    val successText = stringResource(id = R.string.booking_success)
    val failureText = stringResource(id = R.string.booking_failure)

    LaunchedEffect(Unit) {
        viewModel.coldFlowEvents
            .onEach {
                when (it) {
                    MainColdFlowEvent.BookingSuccess -> scaffoldState.snackbarHostState.showSnackbar(
                        message = successText
                    )
                    MainColdFlowEvent.BookingFailure -> scaffoldState.snackbarHostState.showSnackbar(
                        message = failureText
                    )
                }
            }
            .collect()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        scaffoldState = scaffoldState,
    ) {
        SwipeRefresh(
            indicatorPadding = PaddingValues(vertical = with(LocalDensity.current) {
                (toolbarHeightPx + toolbarOffsetHeightPx.value).toDp()
            }),
            state = rememberSwipeRefreshState(isRefreshing = viewState.value.isRefreshing),
            onRefresh = { onSwipeRefreshed() }
        ) {
            RoomList(
                toolbarHeight = toolbarHeight,
                rooms = viewState.value.rooms,
                onButtonTapped = onButtonTapped
            )
        }
        TopAppBar(
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) },
            title = { Text(stringResource(id = R.string.app_name)) }
        )
    }
}

