package lucas.momo.designsystem.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toBrazilianReal(): String {
    val localeBR = Locale("pt", "BR")
    val formatter = NumberFormat.getCurrencyInstance(localeBR)
    return formatter.format(this)
}