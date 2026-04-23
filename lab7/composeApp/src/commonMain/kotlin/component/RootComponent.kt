package component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable


interface RootComponent {
    val childStack: Value<ChildStack<Config, Child>>

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class Second(val component: SecondComponent) : Child
    }
}

class RootComponentImpl(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = { config, context ->
            when (config) {
                is Config.Home -> RootComponent.Child.Home(
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
}