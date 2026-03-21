package lucas.momo.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondaryDark,
    background = BgDark,
    onBackground = TextPrimaryDark,
    surface = BgSurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = BgSurfaceVariantDark,
    onSurfaceVariant = TextPrimaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondaryLight,
    background = BgLight,
    onBackground = TextPrimaryLight,
    surface = BgSurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = BgSurfaceVariantLight,
    onSurfaceVariant = TextPrimaryLight
)

@Composable
fun MiaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dimensions = Dimensions()

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalDimens provides dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}
