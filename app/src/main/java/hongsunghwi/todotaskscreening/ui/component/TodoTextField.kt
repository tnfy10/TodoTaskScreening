package hongsunghwi.todotaskscreening.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hongsunghwi.todotaskscreening.R
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme
import hongsunghwi.todotaskscreening.ui.theme.advancedShadow

@Composable
fun TodoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onClickConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .advancedShadow(
                alpha = 0.1f,
                shadowBlurRadius = 14.8.dp,
                offsetY = (-2).dp
            )
            .background(TodoTaskScreeningTheme.colors.white)
            .fillMaxWidth()
            .padding(vertical = 11.dp, horizontal = 12.dp)
            .navigationBarsPadding()
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .weight(1f)
                .height(48.dp),
            textStyle = TodoTaskScreeningTheme.typography.bodyRegular1,
            singleLine = false
        ) { innerTextField ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = TodoTaskScreeningTheme.colors.gray02,
                        shape = RoundedCornerShape(55.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(R.string.todo_text_field_placholder),
                        style = TodoTaskScreeningTheme.typography.bodyRegular1.copy(
                            color = TodoTaskScreeningTheme.colors.gray03
                        )
                    )
                }
                innerTextField()
            }
        }
        Spacer(Modifier.width(9.dp))
        Image(
            painter = painterResource(
                id = if (value.isNotBlank()) {
                    R.drawable.ic_tick_circle_enabled
                } else {
                    R.drawable.ic_tick_circle_disabled
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClickConfirm
                )
                .size(48.dp)
        )
    }
}

@Preview
@Composable
private fun TodoTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    TodoTaskScreeningTheme {
        TodoTextField(
            value = value,
            onValueChange = {
                value = it
            },
            onClickConfirm = {}
        )
    }
}