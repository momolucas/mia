package lucas.momo.designsystem.utils.extensions

import lucas.momo.designsystem.utils.Constants

fun Double.toBrazilianReal(): String = Constants.CURRENCY_FORMATTER_BR.format(this)

fun Double.toDollar(): String = Constants.CURRENCY_FORMATTER_US.format(this)
