package hongsunghwi.todotaskscreening.ui.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import hongsunghwi.todotaskscreening.ui.theme.NavigationIconButtonSize

@Composable
fun NavigationIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = NavigationIconButtonSize,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = remember { MutableInteractionSource() },
    indication: Indication? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier =
            modifier
                .size(size)
                .clip(CircleShape)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = indication
                ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}