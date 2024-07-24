package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Location
import data.model.localTime

@Composable
internal fun LocationCard(
    location: Location,
    onClicked: ((Location) -> Unit?)? = null
) {
    val modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.large)
        .padding(vertical = 8.dp, horizontal = 16.dp)

    onClicked?.let {
        modifier.clickable { it(location) }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1F), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = location.name,
                style = MaterialTheme.typography.headlineLarge
            )
            AssistChip(
                label = {
                    Text(
                        text = location.regionName,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                onClick = {}
            )
        }
        Text(
            text = location.timeRegion.localTime(),
            style = MaterialTheme.typography.titleLarge
        )
//        ListView(
//            label = {
//                ListViewColumnLabel(
//                    label = location.label,
//                    description = location.regionName,
//                )
//            },
//            endLabel = {
//                ListViewColumnLabel(
//                    label = location.timeRegion.localTime()
//                )
//            }
//        )
    }
}