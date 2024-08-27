package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    startContent: @Composable (() -> Unit)? = null,
    label: String? = null,
    description: String? = null,
    endContent: @Composable (() -> Unit)? = null,
    labelColor: Color = MaterialTheme.colorScheme.onSurface,
    descriptionColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    horizontalSpacedBy: Dp = 8.dp,
    columnPadding: Dp = 4.dp,
) {
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacedBy),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        startContent?.invoke()
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = Arrangement.spacedBy(columnPadding),
        ) {
            if (!label.isNullOrBlank()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = labelColor,
                )
            }
            if (!description.isNullOrBlank()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = descriptionColor,
                )
            }
        }
        endContent?.invoke()
    }
}