package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RadioGroupBottomSheet(
    title: String,
    dismissLabel: String = "Done",
    selectedIndex: Int,
    items: List<RadioRowData>,
    isVisible: Boolean,
    onSelected: (RadioRowData) -> Unit,
    onDismissed: () -> Unit
) {
    if (!isVisible) return
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissed,
        sheetState = modalBottomSheetState,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = AppTypography.titleLarge
            )
            RadioGroup(
                initialIndex = selectedIndex,
                options = items,
                onSelected = { index, _ ->
                    onSelected(items[index])
                }
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onClick = { onDismissed() },
            ) {
                Text(dismissLabel, modifier = Modifier.padding(vertical = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}