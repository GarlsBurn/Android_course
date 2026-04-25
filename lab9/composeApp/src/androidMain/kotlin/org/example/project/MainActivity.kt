package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import component.RootComponentImpl
import di.AndroidInjectionCompanion
import di.InjectionCompanion


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val root = RootComponentImpl(
            httpClient = InjectionCompanion.httpClient,
            dataStore = AndroidInjectionCompanion.getDataStore(this),
            componentContext = defaultComponentContext()
        )

        setContent {
            App(root)
        }
    }
}