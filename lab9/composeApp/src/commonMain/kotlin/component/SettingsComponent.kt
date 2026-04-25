package component


import androidx.datastore.core.DataStore
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.launch
import preferences.Preferences

interface SettingsComponent {
    fun updatePreferences(block: suspend Preferences.() -> Preferences)
}

class SettingsComponentImpl(
    private val dataStore: DataStore<Preferences>,
    componentContext: ComponentContext
) : SettingsComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()

    override fun updatePreferences(block: suspend Preferences.() -> Preferences) {
        scope.launch {
            dataStore.updateData(block)
        }
    }
}