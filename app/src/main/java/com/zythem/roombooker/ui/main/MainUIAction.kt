package com.zythem.roombooker.ui.main

sealed class MainUIAction {
    class ButtonTapped(val name: String): MainUIAction()
    object SwipeRefreshed: MainUIAction()
}
