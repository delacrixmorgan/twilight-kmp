package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ListView(
    modifier: Modifier = Modifier,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    endLabel: @Composable (() -> Unit)? = null,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
) {
    Row(
        modifier = modifier.then(Modifier.fillMaxWidth()),
        verticalAlignment = verticalAlignment,
    ) {
        if (startIcon != null) {
            startIcon()
        } else {
            Spacer(modifier = Modifier.width(16.dp))
        }

        Box(modifier = Modifier.weight(1F)) {
            label?.invoke()
        }
        endLabel?.let {
            Box {
                endLabel.invoke()
            }
        }

        if (endIcon != null) {
            endIcon()
        } else {
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

//@Composable
//fun ListViewColumnLabel(
//    label: String? = null,
//    description: String? = null,
//    endIcon: @Composable (() -> Unit)? = null,
//    labelColor: Color = PeaksColor.Gray900.color,
//    descriptionColor: Color = PeaksColor.Gray700.color,
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = RC.dimen.spacing_xs)),
//    ) {
//        Column(
//            modifier = Modifier.padding(vertical = dimensionResource(id = RC.dimen.spacing_l)),
//            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = RC.dimen.spacing_xs)),
//        ) {
//            if (!label.isNullOrBlank()) {
//                Text(
//                    text = label,
//                    style = PeaksTypography.BodyMD.style().copy(fontWeight = FontWeight.Medium),
//                    color = labelColor,
//                )
//            }
//            if (!description.isNullOrBlank()) {
//                Text(
//                    text = description,
//                    style = PeaksTypography.BodySM.style(),
//                    color = descriptionColor,
//                )
//            }
//        }
//
//        endIcon?.invoke()
//    }
//}