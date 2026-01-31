package lucas.momo.designsystem.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toBrazilianDate(): String {
    val formatter = DateTimeFormatter
        .ofPattern("dd/MM/yyyy")
        .withZone(ZoneId.of("America/Sao_Paulo"))

    return formatter.format(this)
}