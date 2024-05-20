package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Location
import data.model.localTime

@Composable
internal fun LocationListRow(
    location: Location,
    onClicked: ((Location) -> Unit?)? = null
) {
    Box(
        modifier = Modifier
            .clickable { onClicked?.invoke(location) }
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.small)
            .padding(8.dp),
    ) {
        ListView(
            label = {
                ListViewColumnLabel(
                    label = location.label,
                    description = location.customRegionName.ifBlank { location.zoneId },
                )
            },
            endLabel = {
                ListViewColumnLabel(
                    label = location.timeRegion.localTime()
                )
            }
        )
    }
}