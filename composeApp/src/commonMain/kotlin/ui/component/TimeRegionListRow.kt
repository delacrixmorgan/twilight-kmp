package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import data.kalika.model.TimeRegion

@Composable
internal fun TimeRegionListRow(
    timeRegion: TimeRegion,
    selected: Boolean = false,
    onClicked: (TimeRegion) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.small)
            .clickable { onClicked(timeRegion) }
            .padding(8.dp),
    ) {
        ListItem(
            modifier = Modifier.padding(vertical = 8.dp),
            startContent = {
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
            },
            label = timeRegion.city,
            description = timeRegion.zone,
            endContent = {
                Text(
                    text = timeRegion.localTime(),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        )
    }
}