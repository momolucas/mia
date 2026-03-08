package lucas.momo.designsystem.utils.extensions

import lucas.momo.designsystem.utils.Constants
import java.math.BigDecimal

fun BigDecimal.toBrazilianReal(): String = Constants.CURRENCY_FORMATTER_BR.format(this)