package com.ovi.apileveltest.presentation.screen.event

sealed class MainUiEvent {
    data object GetFakeData : MainUiEvent()
}