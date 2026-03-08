package lucas.momo.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import lucas.momo.designsystem.R
import lucas.momo.designsystem.theme.LocalDimens
import lucas.momo.designsystem.utils.transformations.CurrencyVisualTransformation

@Composable
fun CurrencyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    visualTransformation: CurrencyVisualTransformation = CurrencyVisualTransformation.real(),
) {
    var hasBeenFocused by remember { mutableStateOf(false) }

    val isRequiredError = hasBeenFocused && value.isBlank()

    val onValueChangeFiltered = { newValue: String ->
        val digitsOnly = newValue.filter { it.isDigit() }
        onValueChange(digitsOnly)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChangeFiltered,
        label = { Text(text = stringResource(label), style = MaterialTheme.typography.bodySmall) },
        isError = isRequiredError,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(LocalDimens.current.textFieldHeight)
            .onFocusChanged { focusState ->
                if (focusState.isFocused) hasBeenFocused = true
            },
        shape = MaterialTheme.shapes.small,
        colors = defaultOutlinedTextFieldColors(),
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodySmall,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
        supportingText = if (isRequiredError) {
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.ErrorOutline,
                        contentDescription = stringResource(R.string.content_description_error),
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .size(LocalDimens.current.iconSizeSmall)
                            .padding(end = LocalDimens.current.tinyPadding)
                    )
                    Text(
                        text = stringResource(R.string.text_field_required_field),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        } else null
    )
}
