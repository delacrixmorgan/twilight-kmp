package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
internal fun ListItemRow(
    label: String,
    description: String,
    endLabel: String? = null,
    selected: Boolean = false,
    onClicked: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.large)
            .then(if (onClicked != null) Modifier.clickable { onClicked() } else Modifier)
            .padding(8.dp)
    ) {
        ListItem(
            modifier = Modifier.padding(vertical = 8.dp),
            startContent = {
                if (onClicked != null) {
                    if (selected) {
                        Icon(
                            Icons.Rounded.CheckCircle,
                            modifier = Modifier.padding(16.dp),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            Icons.Rounded.RadioButtonUnchecked,
                            modifier = Modifier.padding(16.dp),
                            contentDescription = null
                        )
                    }
                } else {
                    Spacer(Modifier.width(16.dp))
                }
            },
            label = label,
            description = description,
            endContent = {
                if (!endLabel.isNullOrBlank()) {
                    Text(
                        text = endLabel,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                } else {
                    Spacer(Modifier.width(16.dp))
                }
            }
        )
    }
}