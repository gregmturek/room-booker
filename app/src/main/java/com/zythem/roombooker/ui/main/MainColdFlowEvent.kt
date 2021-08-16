package com.zythem.roombooker.ui.main

sealed class MainColdFlowEvent {
    object BookingSuccess: MainColdFlowEvent()
    object BookingFailure: MainColdFlowEvent()
}
