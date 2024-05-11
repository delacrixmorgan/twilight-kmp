package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    haptic: HapticFeedback = LocalHapticFeedback.current,
    initialIndex: Int? = null,
    options: List<RadioRowData>,
    onSelected: (Int, String) -> Unit,
) {
    var selectedIndex by remember(initialIndex) { mutableStateOf(initialIndex) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
                selected = selected,
            )
        }
    }
}

@Composable
private fun RadioRow(
    isSelected: Boolean,
    option: RadioRowData,
    selected: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .clickable { selected() }
            .background(MaterialTheme.colorScheme.surfaceContainerLow, shape = MaterialTheme.shapes.small)
            .padding(8.dp),
    ) {
        ListView(
            label = { ListViewColumnLabel(label = option.label, description = option.description) },
            endIcon = {
                RadioButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    selected = isSelected,
                    onClick = selected,
                )
            }
        )
    }
}

data class RadioRowData(
    val id: String = "",
    val label: String,
    val description: String? = null,
    val iconDrawableRes: Int? = null,
)