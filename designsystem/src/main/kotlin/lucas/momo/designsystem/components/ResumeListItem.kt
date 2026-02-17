package lucas.momo.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lucas.momo.designsystem.R
import lucas.momo.designsystem.ThemePreviews
import lucas.momo.designsystem.theme.Gain
import lucas.momo.designsystem.theme.LocalDimens
import lucas.momo.designsystem.theme.MiaTheme
import lucas.momo.designsystem.theme.Positive

data class ResumeListItemData(
    val title: String,
    val titleEnd: String? = null,
    val firstLineLabel: String? = null,
    val firstLineLabelEnd: String? = null,
    val secondLineLabel: String? = null,
    val secondLineLabelEnd: String? = null
)

@Composable
fun ResumeListItem(
    data: ResumeListItemData,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(LocalDimens.current.smallPadding)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = LocalDimens.current.smallPadding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            data.titleEnd?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            data.firstLineLabel?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = LocalDimens.current.smallPadding)
                )
            }
            data.firstLineLabelEnd?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = LocalDimens.current.smallPadding)
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = LocalDimens.current.smallPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.brand_inter),
                contentDescription = "Logo Corretora",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(12.dp)
                    .padding(horizontal = LocalDimens.current.smallPadding)
            )
            Row {
                data.secondLineLabel?.let {
                    Text(
                        text = it,
                        color = Positive,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                data.secondLineLabelEnd?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(start = LocalDimens.current.mediumPadding),
                        color = Gain,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

}

@ThemePreviews
@Composable
private fun FullListItemPreview() {
    MiaTheme {
        ResumeListItem(
            ResumeListItemData(
                title = "Debenture CRMG15",
                titleEnd = "R$ 5269,95",
                firstLineLabel = "Vencimento: 15/12/2030",
                firstLineLabelEnd = "Yield: IPCA + 6,55%",
                secondLineLabel = "\u2191 R$ 5269,95",
                secondLineLabelEnd = "\u21A9 R$ 5269,95"
            )
        )
    }
}
