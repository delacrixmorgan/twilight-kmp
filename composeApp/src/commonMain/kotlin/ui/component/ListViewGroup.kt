package ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListViewGroup(
    modifier: Modifier = Modifier,
    data: List<@Composable (ColumnScope.() -> Unit)>,
    divider: @Composable (() -> Unit)? = null,
    showTopDivider: Boolean = false,
) {
    Column(modifier) {
        if (showTopDivider) divider?.invoke()
        data.forEach { listView ->
            listView()
            divider?.invoke()
        }
    }
}
