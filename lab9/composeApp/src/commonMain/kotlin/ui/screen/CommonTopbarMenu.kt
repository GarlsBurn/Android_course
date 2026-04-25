package ui.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun CommonTopbarMenu(
    goToSettings: () -> Unit,
    items: @Composable (dismiss: () -> Unit) -> Unit = {}
) = Box {
    var expanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        items { expanded = false }

        DropdownMenuItem(
            text = { Text("Настройки") },
            leadingIcon = {
                Icon(Icons.Default.Settings, contentDescription = null)
            },
            onClick = {
                expanded = false
                goToSettings()
            }
        )
    }

    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Default.MoreVert, contentDescription = null)
    }
}