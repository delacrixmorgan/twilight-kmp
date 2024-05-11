package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.TimeRegion
import data.model.localTime

@Composable
internal fun TimeRegionListRow(
    timeRegion: TimeRegion,
    clicked: (TimeRegion) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { clicked(timeRegion) }
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.small)
            .padding(8.dp),
    ) {
        ListView(
            label = {
                ListViewColumnLabel(
                    label = timeRegion.city,
                    description = timeRegion.zone
                )
            },
            endLabel = {
                ListViewColumnLabel(
                    label = timeRegion.localTime()
                )
            }
        )
    }
}