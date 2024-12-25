package com.ovi.apileveltest.presentation.screen.ui

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ovi.apileveltest.presentation.screen.event.MainUiEvent
import com.ovi.apileveltest.presentation.screen.state.MainUiState
import com.ovi.apileveltest.presentation.screen.viewmodel.MainViewModel
import com.ovi.apileveltest.presentation.theme.ApiLevelTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.handleEvent(MainUiEvent.GetFakeData)


        enableEdgeToEdge()
        setContent {
            ApiLevelTestTheme {
                val state = viewModel.uiState.collectAsState()
                Greeting(state = state.value)
            }
        }
    }
}

@Composable
fun Greeting(state: MainUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = state.data.title
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier,
            text = state.data.description
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier,
            text = state.data.source
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiLevelTestTheme {
        Greeting(state = MainUiState())
    }
}