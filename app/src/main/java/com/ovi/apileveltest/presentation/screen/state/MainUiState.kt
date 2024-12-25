package com.ovi.apileveltest.presentation.screen.state

import com.ovi.apileveltest.domain.model.FakeModel

data class MainUiState(
    val isLoading: Boolean = false,
    val data: FakeModel = FakeModel(),
    val error: String = ""
)