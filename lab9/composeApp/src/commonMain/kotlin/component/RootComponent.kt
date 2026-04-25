package component


import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import io.ktor.client.HttpClient
import kotlinx.serialization.Serializable


import androidx.datastore.core.DataStore
import com.arkivanov.decompose.router.stack.bringToFront
import kotlinx.coroutines.flow.Flow
import preferences.Preferences

interface RootComponent {
    val childStack: Value<ChildStack<Config, Child>>
    val preferences: Flow<Preferences>

    fun goToSettings()

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class Second(val component: SecondComponent) : Child
        class Settings(val component: SettingsComponent) : Child
    }
}

class RootComponentImpl(
    private val httpClient: HttpClient,
    private val dataStore: DataStore<Preferences>,
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val preferences: Flow<Preferences>
        get() = dataStore.data

    override fun goToSettings() {
        navigation.bringToFront(Config.Settings)
    }

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = { config, context ->
            when (config) {
                Config.Home -> RootComponent.Child.Home(
                    HomeComponentImpl(
                        onNavigateToSecondScreen = { param ->
                            navigation.pushNew(Config.Second(param))
                        },
                        componentContext = context
                    )
                )

                is Config.Second -> RootComponent.Child.Second(
                    SecondComponentImpl(
                        param = config.param,
                        httpClient = httpClient,
                        componentContext = context
                    )
                )

                Config.Settings -> RootComponent.Child.Settings(
                    SettingsComponentImpl(
                        dataStore = dataStore,
                        componentContext = context
                    )
                )
            }
        }
    )
}

@Serializable
sealed interface Config {
    @Serializable
    data object Home : Config

    @Serializable
    data class Second(val param: String) : Config

    @Serializable
    data object Settings : Config
}