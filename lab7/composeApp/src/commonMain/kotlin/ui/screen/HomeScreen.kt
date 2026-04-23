package ui.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import component.HomeComponent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(component: HomeComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Домашний экран")
                }
            )
        }
    ) { contentPadding ->
        var text by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = {
                    Text("Параметр второго экрана")
                }
            )

            Button(
                onClick = {
                    component.navigateToSecondScreen(text)
                }
            ) {
                Text("Перейти на второй экран")
            }
        }
    }
}