package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import component.RootComponent
import org.jetbrains.compose.resources.painterResource

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import ui.screen.HomeScreen
import ui.screen.SecondScreen



import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children

@Composable
fun App(rootComponent: RootComponent) {
    MaterialTheme {
        Children(rootComponent.childStack) {
            when (val child = it.instance) {
                is RootComponent.Child.Home -> HomeScreen(child.component)
                is RootComponent.Child.Second -> SecondScreen(child.component)
            }
        }
    }
}