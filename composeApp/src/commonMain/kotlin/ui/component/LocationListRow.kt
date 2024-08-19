package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.location.model.Location
import data.utils.localTime

@Composable
internal fun LocationListRow(
    location: Location,
    onClicked: ((Location) -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .then(if (onClicked != null) Modifier.clickable { onClicked(location) } else Modifier)
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.large)
            .padding(8.dp)
    ) {
        ListItem(
            label = {
                ListItemColumnLabel(
                    label = location.name,
                    description = location.regionName,
                )
            },
            endLabel = {
                ListItemColumnLabel(
                    label = location.timeRegion.localTime()
                )
            }
        )
    }
}