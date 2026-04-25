package preferences


import androidx.compose.runtime.compositionLocalOf
import kotlinx.serialization.Serializable

@Serializable
data class Preferences(
    val theme: ThemePreference = ThemePreference.SYSTEM
) {
    @Serializable
    enum class ThemePreference {
        SYSTEM,
        LIGHT,
        DARK
    }
}

val LocalPreferences = compositionLocalOf { Preferences() }