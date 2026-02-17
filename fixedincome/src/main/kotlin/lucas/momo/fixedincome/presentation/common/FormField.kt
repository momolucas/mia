package lucas.momo.fixedincome.presentation.common

data class FormField<T>(
    var value: T,
    var isValid: Boolean = false
)
