package lucas.momo.designsystem.utils.extensions

import lucas.momo.designsystem.utils.Constants.BRAZILIAN_DATE_PATTERN
import lucas.momo.designsystem.utils.Constants.BRAZILIAN_ZONE_ID
import java.time.Instant
import java.time.format.DateTimeFormatter

fun Instant.toBrazilianDate(): String {
    val formatter = DateTimeFormatter
        .ofPattern(BRAZILIAN_DATE_PATTERN)
        .withZone(BRAZILIAN_ZONE_ID)

    return formatter.format(this)
}