package ui.screen

import component.SecondComponent


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.RequestState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SecondScreen(component: SecondComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Второй экран")
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            when (val state = component.requestState) {
                RequestState.Idle -> {
                    Button(onClick = { component.fetch() }) {
                        Text("Загрузить")
                    }
                }

                RequestState.Loading -> {
                    Row {
                        Spacer(Modifier.weight(1f))
                        CircularProgressIndicator()
                        Spacer(Modifier.weight(1f))
                    }
                }

                RequestState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Произошла ошибка во время загрузки")
                        Button(onClick = { component.retry() }) {
                            Text("Попробовать ещё раз")
                        }
                    }
                }

                is RequestState.Success -> {
                    SelectionContainer {
                        Text(state.value.toString())
                    }
                }
            }
        }
    }
}