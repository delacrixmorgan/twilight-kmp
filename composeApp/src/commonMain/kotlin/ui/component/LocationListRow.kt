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
import data.model.LocationType
import data.model.localTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.AppTheme

@Composable
internal fun LocationListRow(
    location: Location,
    onClicked: ((Location) -> Unit?)? = null
) {
    val modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.large)
        .padding(8.dp)

    onClicked?.let {
        modifier.clickable { it(location) }
    }
    Box(modifier = modifier) {
        ListView(
            label = {
                ListViewColumnLabel(
                    label = location.label,
                    description = location.regionName,
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