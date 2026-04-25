package ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.SettingsComponent
import preferences.LocalPreferences
import preferences.Preferences

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingsScreen(component: SettingsComponent) {
    val preferences = LocalPreferences.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Настройки")
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding
        ) {
            item {
                var showThemeDialog by remember { mutableStateOf(false) }

                ListItem(
                    leadingContent = {
                        Icon(Icons.Default.DarkMode, contentDescription = null)
                    },
                    headlineContent = {
                        Text("Тема оформления")
                    },
                    supportingContent = {
                        Text(preferences.theme.toDisplayString())
                    },
                    modifier = Modifier.clickable {
                        showThemeDialog = true
                    }
                )

                if (showThemeDialog) {
                    ThemeChooserDialog(
                        currentTheme = preferences.theme,
                        onDismiss = {
                            showThemeDialog = false
                        },
                        onConfirm = { theme ->
                            component.updatePreferences {
                                copy(theme = theme)
                            }
                            showThemeDialog = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeChooserDialog(
    currentTheme: Preferences.ThemePreference,
    onDismiss: () -> Unit,
    onConfirm: (Preferences.ThemePreference) -> Unit,
) {
    var selectedTheme by remember { mutableStateOf(currentTheme) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Выберите тему оформления")
        },
        text = {
            Column {
                Preferences.ThemePreference.entries.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedTheme = theme
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = theme == selectedTheme,
                            onClick = {
                                selectedTheme = theme
                            }
                        )

                        Text(
                            text = theme.toDisplayString(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(selectedTheme)
                }
            ) {
                Text("Выбрать")
            }
        }
    )
}

@Composable
private fun Preferences.ThemePreference.toDisplayString(): String {
    return when (this) {
        Preferences.ThemePreference.SYSTEM -> "Системная"
        Preferences.ThemePreference.LIGHT -> "Светлая"
        Preferences.ThemePreference.DARK -> "Тёмная"
    }
}