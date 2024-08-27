package ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListView(
    modifier: Modifier = Modifier,
    data: List<@Composable (ColumnScope.() -> Unit)>,
    divider: @Composable (() -> Unit)? = null,
) {
    Column(modifier) {
        data.forEachIndexed { index, listView ->
            if (index != 0) divider?.invoke()
            listView()
        }
    }
}
