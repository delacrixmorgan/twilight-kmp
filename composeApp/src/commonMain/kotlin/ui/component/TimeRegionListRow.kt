package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.model.TimeRegion
import data.model.localTime
import ui.theme.Typography

@Composable
internal fun TimeRegionListRow(timeRegion: TimeRegion) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.align(alignment = Alignment.CenterVertically), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = timeRegion.city,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = timeRegion.zone,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = Typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = timeRegion.localTime(),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(alignment = Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.labelLarge
            )
        }
    }
}