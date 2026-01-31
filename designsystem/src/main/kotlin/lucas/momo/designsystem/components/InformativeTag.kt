package lucas.momo.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import lucas.momo.designsystem.ThemePreviews
import lucas.momo.designsystem.theme.MiaTheme

@Composable
fun InformativeTag(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {
    Surface(
        modifier = modifier.widthIn(),
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor,
        tonalElevation = 0.dp
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 4.dp
            ),
            color = textColor,
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@ThemePreviews
@Composable
fun InformativeTagDefaultPreview() {
    MiaTheme {
        InformativeTag(text = LoremIpsum(10).values.first())
    }
}
