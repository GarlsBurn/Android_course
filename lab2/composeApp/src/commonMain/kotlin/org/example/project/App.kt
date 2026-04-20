package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun ShoppingListElement(description: String) {
    Text(description, color = Color.Blue)
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val scrollState = rememberScrollState()
        Column(Modifier.verticalScroll(scrollState).fillMaxSize()) {
            Card (modifier = Modifier.padding(8.dp),){
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

                Column {
                    Text("Compose Multiplatform",
                        style = MaterialTheme.typography.titleSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1)
                    Text("Создавайте универсальные интерфейсы для Android, iOS, десктопа и веба, которые органично выглядят на всех устройствах.",
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2)
                }
            }
        }
            repeat(10) {
                ShoppingListElement("Продукт №$it")
            }
        }
    }
}