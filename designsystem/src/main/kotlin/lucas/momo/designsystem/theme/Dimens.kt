package lucas.momo.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val tinyPadding: Dp = 2.dp,
    val smallPadding: Dp = 4.dp,
    val mediumPadding: Dp = 8.dp,
    val largePadding: Dp = 16.dp,
    val extraLargePadding: Dp = 24.dp,

    val smallCornerRadius: Dp = 8.dp,
    val mediumCornerRadius: Dp = 12.dp,
    val largeCornerRadius: Dp = 16.dp,

    val smallElevation: Dp = 2.dp,
    val mediumElevation: Dp = 4.dp,
    val largeElevation: Dp = 8.dp,

    val iconSizeSmall: Dp = 16.dp,
    val iconSizeMedium: Dp = 24.dp,
    val iconSizeLarge: Dp = 32.dp,

    val buttonHeight: Dp = 48.dp,
    val appBarHeight: Dp = 56.dp
)

val LocalDimens = staticCompositionLocalOf { Dimensions() }