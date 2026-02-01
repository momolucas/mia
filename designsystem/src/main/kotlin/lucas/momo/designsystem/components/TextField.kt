package lucas.momo.designsystem.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lucas.momo.designsystem.R
import lucas.momo.designsystem.theme.LocalDimens

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        isError = isError,
        singleLine = true,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodySmall,
        colors = defaultOutlinedTextFieldColors(),
        supportingText = supportingText?.let { { Text(text = it) } },
    )
}

@Composable
fun RequiredTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) {
    var wasFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (!wasFocused) {
                wasFocused = true
            }
            onValueChange(newValue)
        },
        label = { Text(text = stringResource(label)) },
        isError = wasFocused && isError,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = defaultOutlinedTextFieldColors(),
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodySmall,
        supportingText = if (wasFocused && isError) {
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

@Composable
fun defaultOutlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
    cursorColor = MaterialTheme.colorScheme.primary,
    errorBorderColor = MaterialTheme.colorScheme.error,
    errorCursorColor = MaterialTheme.colorScheme.error,
    errorLabelColor = MaterialTheme.colorScheme.error,
)