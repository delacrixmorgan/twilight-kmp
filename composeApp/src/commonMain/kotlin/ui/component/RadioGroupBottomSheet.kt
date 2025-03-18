package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.core.DragIndication
import com.composables.core.ModalBottomSheet
import com.composables.core.ModalBottomSheetState
import com.composables.core.Scrim
import com.composables.core.Sheet
import ui.theme.AppTypography

@Composable
internal fun RadioGroupBottomSheet(
    sheetState: ModalBottomSheetState,
    title: String,
    dismissLabel: String = "Done",
    selectedIndex: Int,
    items: List<RadioRowData>,
    onSelected: (RadioRowData) -> Unit,
    onDismissed: () -> Unit
) {
    ModalBottomSheet(state = sheetState, onDismiss = onDismissed) {
        Scrim()
        Sheet(
            Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical).asPaddingValues()),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    DragIndication(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .background(MaterialTheme.colorScheme.onSecondaryContainer, RoundedCornerShape(100))
                            .width(32.dp)
                            .height(4.dp)
                    )
                }
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
}