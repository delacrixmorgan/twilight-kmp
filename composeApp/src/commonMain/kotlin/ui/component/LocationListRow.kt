package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import data.location.model.Location
import data.utils.localTime

@Composable
internal fun LocationListRow(
    location: Location
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.large)
            .padding(8.dp)
    ) {
        ListItem(
            modifier = Modifier.padding(vertical = 8.dp),
            startContent = {
                Spacer(Modifier.width(16.dp))
            },
            label = location.name,
            description = location.regionName,
            endContent = {
                Text(
                    text = location.timeRegion.localTime(),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        )
    }
}