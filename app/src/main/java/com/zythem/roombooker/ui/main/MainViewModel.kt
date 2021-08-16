package com.zythem.roombooker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zythem.roombooker.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _viewState: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _coldFlowEvents = Channel<MainColdFlowEvent>(Channel.BUFFERED)
    val coldFlowEvents = _coldFlowEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            getRooms()
        }
    }

    private suspend fun getRooms() {
        val rooms = repository.getRooms()
        _viewState.value = _viewState.value.copy(rooms = rooms)
    }

    fun onAction(uiAction: MainUIAction) {
        when (uiAction) {
            is MainUIAction.ButtonTapped -> {
                viewModelScope.launch {
                    val success = repository.bookRoom(uiAction.name)
                    _coldFlowEvents.send(
                        if (success)
                            MainColdFlowEvent.BookingSuccess
                        else
                            MainColdFlowEvent.BookingFailure
                    )
                    getRooms()
                }
            }
            is MainUIAction.SwipeRefreshed -> {
                viewModelScope.launch {
                    _viewState.value = _viewState.value.copy(isRefreshing = true)
                    getRooms()
                    _viewState.value = _viewState.value.copy(isRefreshing = false)
                }
            }
        }
    }
}
