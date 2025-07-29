package hongsunghwi.todotaskscreening.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import hongsunghwi.todotaskscreening.R
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TodoItem(
    text: String,
    onClickComplete: () -> Unit,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val offsetX = remember { Animatable(0f) }
    val deleteButtonWidth = 66.dp
    val deleteButtonWidthPx = with(density) { deleteButtonWidth.toPx() }
    val scope = rememberCoroutineScope()
    val draggableState = rememberDraggableState { delta ->
        scope.launch {
            val newOffset = (offsetX.value + delta).coerceIn(-deleteButtonWidthPx, 0f)
            offsetX.snapTo(newOffset)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(TodoTaskScreeningTheme.colors.red)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(deleteButtonWidth)
                .fillMaxHeight()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClickDelete
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.todo_item_delete),
                style = TodoTaskScreeningTheme.typography.labelBold.copy(
                    color = TodoTaskScreeningTheme.colors.white
                )
            )
        }

        Row(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        scope.launch {
                            if (offsetX.value < -deleteButtonWidthPx / 2) {
                                offsetX.animateTo(
                                    -deleteButtonWidthPx,
                                    animationSpec = tween(durationMillis = 300)
                                )
                            } else {
                                offsetX.animateTo(0f, animationSpec = tween(durationMillis = 300))
                            }
                        }
                    }
                )
                .background(TodoTaskScreeningTheme.colors.white),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = onClickComplete
                    )
                    .padding(start = 4.dp, end = 2.dp)
                    .size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_tick_circle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = text,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f),
                style = TodoTaskScreeningTheme.typography.bodyRegular2
            )
            Spacer(Modifier.width(16.dp))
        }
    }
}

@Preview
@Composable
private fun TodoItemOneLinePreview() {
    TodoTaskScreeningTheme {
        TodoItem(
            text = "Lorem Ipsum is simply dummy text",
            onClickComplete = {},
            onClickDelete = {}
        )
    }
}

@Preview
@Composable
private fun TodoItemTwoLinePreview() {
    TodoTaskScreeningTheme {
        TodoItem(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            onClickComplete = {},
            onClickDelete = {}
        )
    }
}