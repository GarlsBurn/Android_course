package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch


@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    MaterialTheme {

        val lazyColumnState = rememberLazyListState()
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        var showDialog by remember { mutableStateOf(false) }
        var counter by remember { mutableIntStateOf(0) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Верхняя панель")
                    },
                )
            },
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = {
                        Text("Добавить")
                    },
                    icon = {
                        Icon(Icons.Default.Add, contentDescription = null)
                    },
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Нажата кнопка")
                        }
                    },
                    expanded = !lazyColumnState.lastScrolledForward
                )
            }
        ) { contentPadding ->

            LazyColumn(
                contentPadding = contentPadding,
                state = lazyColumnState,
                modifier = Modifier.fillMaxWidth()
            ) {

                item {
                    Button(onClick = {
                        counter++
                    }) {
                        Text("Увеличить счётчик")
                    }

                    Button(onClick = {
                        showDialog = true
                    }) {
                        Text("Сбросить счётчик")
                    }

                    Text("Счётчик: $counter")

                    Spacer(Modifier.height(32.dp))

                    ElevatedButton(onClick = {}) {
                        Text("Возвышенная")
                    }

                    Button(onClick = {}) {
                        Text("Заполненная")
                    }

                    FilledTonalButton(onClick = {}) {
                        Text("Заполненная тоновая")
                    }

                    OutlinedButton(onClick = {}) {
                        Text("Контурная")
                    }

                    TextButton(onClick = {}) {
                        Text("Текстовая")
                    }

                    Spacer(Modifier.height(32.dp))

                    FilledTonalButton(onClick = {}) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Text("С иконкой")
                    }
                }

                items(100) {
                    Text("Предмет $it")
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog = false
                            counter = 0
                        }) {
                            Text("Подтвердить")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Отменить")
                        }
                    },
                    title = {
                        Text("Сбросить счётчик?")
                    },
                    text = {
                        Text("Прогресс будет потерян")
                    },
                    icon = {
                        Icon(Icons.Default.Warning, contentDescription = null)
                    }
                )
            }
        }
    }
}