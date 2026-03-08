package lucas.momo.designsystem.utils.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import lucas.momo.designsystem.utils.emptyString
import lucas.momo.designsystem.utils.extensions.toBrazilianReal
import lucas.momo.designsystem.utils.extensions.toDollar

class CurrencyVisualTransformation(val toCurrency: (Double) -> String) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }

        if (digits.isEmpty()) {
            return TransformedText(AnnotatedString(emptyString()), OffsetMapping.Identity)
        }

        val formattedText = toCurrency((digits.toLongOrNull() ?: DEFAULT_VALUE) / CENTS_DIVISOR)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = formattedText.length
            override fun transformedToOriginal(offset: Int): Int = text.length
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    companion object {
        /* Constants */
        private const val DEFAULT_VALUE = 0L
        private const val CENTS_DIVISOR = 100.0

        /* Factories */
        fun real() = CurrencyVisualTransformation { it.toBrazilianReal() }
        fun dollar() = CurrencyVisualTransformation { it.toDollar() }
    }
}
