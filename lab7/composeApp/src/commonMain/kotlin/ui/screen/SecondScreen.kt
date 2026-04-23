package ui.screen

import component.SecondComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
            SelectionContainer {
                Text(component.param)
            }
        }
    }
}