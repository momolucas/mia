package lucas.momo.designsystem.utils

import java.text.NumberFormat
import java.time.ZoneId
import java.util.Locale

object Constants {
    const val LOCALE_LANGUAGE_PT = "pt"
    const val LOCALE_LANGUAGE_EN = "en"

    const val LOCALE_COUNTRY_BR = "BR"
    const val LOCALE_COUNTRY_US = "US"

    const val BRAZILIAN_DATE_PATTERN = "dd/MM/yyyy"

    val BRAZILIAN_ZONE_ID: ZoneId by lazy {
        ZoneId.of("America/Sao_Paulo")
    }

    val BRAZILIAN_LOCALE: Locale by lazy {
        Locale(LOCALE_LANGUAGE_PT, LOCALE_COUNTRY_BR)
    }

    val USA_LOCALE: Locale by lazy {
        Locale(LOCALE_LANGUAGE_EN, LOCALE_COUNTRY_US)
    }

    val CURRENCY_FORMATTER_BR: NumberFormat by lazy {
        NumberFormat.getCurrencyInstance(BRAZILIAN_LOCALE)
    }

    val CURRENCY_FORMATTER_US: NumberFormat by lazy {
        NumberFormat.getCurrencyInstance(USA_LOCALE)
    }
}