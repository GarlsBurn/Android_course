package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res

import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource



@Composable
@Preview
fun App() {
    Column {
        var count by remember { mutableIntStateOf(1) }

        Button(onClick = {
            count += 1
        }) {
            Text(stringResource(Res.string.increment))
        }
        Button(onClick = {
            count -= 1
        }) {
            Text(stringResource(Res.string.decrement))
        }

        Text(pluralStringResource(Res.plurals.things, count, count))
    }
}