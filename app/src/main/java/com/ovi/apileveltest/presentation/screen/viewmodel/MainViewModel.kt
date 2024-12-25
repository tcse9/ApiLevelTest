package com.ovi.apileveltest.presentation.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovi.apileveltest.data.ApiResult
import com.ovi.apileveltest.domain.FakeDataUseCase
import com.ovi.apileveltest.presentation.screen.event.MainUiEvent
import com.ovi.apileveltest.presentation.screen.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fakeDataUseCase: FakeDataUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: MainUiEvent) {
        when(event) {
            is MainUiEvent.GetFakeData -> getFakeFakeData()
        }
    }

    private fun getFakeFakeData() {
        viewModelScope.launch {
            fakeDataUseCase().collect {
                when(it) {
                    is ApiResult.Error -> {}
                    ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        _uiState.value = MainUiState(isLoading = false, data = it.data, error = "")
                    }
                }
            }
        }
    }
}