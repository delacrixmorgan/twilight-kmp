package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.timescape.model.TimeRegion

@Composable
internal fun TimeRegionListRow(
    timeRegion: TimeRegion,
    selected: Boolean = false,
    onClicked: (TimeRegion) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClicked(timeRegion) }
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.small)
            .padding(8.dp),
    ) {
        ListItem(
            startIcon = {
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
            label = {
                ListItemColumnLabel(
                    label = timeRegion.city,
                    description = timeRegion.zone
                )
            },
            endLabel = {
                ListItemColumnLabel(
                    label = timeRegion.localTime()
                )
            }
        )
    }
}