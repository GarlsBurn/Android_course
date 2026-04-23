package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold { contentPadding ->
            var state by remember { mutableStateOf(false) }
            Column(Modifier.padding(contentPadding)) {
                Button(onClick = { state = !state }) {
                    Text("Переключить")
                }
                Text("Состояние: $state")
            }
        }
    }
}