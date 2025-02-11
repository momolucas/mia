package lucas.momo.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Roboto = FontFamily.Default

val Typography = Typography(
    // Titles
    displayLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp
    ),

    // Small sections titles
    headlineLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp
    ),

    // Body text
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.4.sp
    ),

    // Buttons
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp
    )
)
