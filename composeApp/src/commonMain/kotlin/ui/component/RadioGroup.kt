package ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
internal fun RadioGroup(
    modifier: Modifier = Modifier,
    haptic: HapticFeedback = LocalHapticFeedback.current,
    initialIndex: Int? = null,
    options: List<RadioRowData>,
    onSelected: (Int, String) -> Unit,
) {
    var selectedIndex by remember(initialIndex) { mutableStateOf(initialIndex) }
    Column(modifier = modifier) {
        options.forEachIndexed { index, option ->
            val isSelected = index == selectedIndex
            val selected = {
                selectedIndex = index
                onSelected(index, option.id)
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
            RadioRow(
                isSelected = isSelected,
                option = option,
                onSelected = selected,
            )
        }
    }
}

@Composable
private fun RadioRow(
    isSelected: Boolean,
    option: RadioRowData,
    onSelected: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onSelected() }.padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelected,
        )

        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = option.label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (!option.description.isNullOrBlank()) {
                Text(
                    text = option.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

data class RadioRowData(
    val id: String = "",
    val label: String,
    val description: String? = null,
)