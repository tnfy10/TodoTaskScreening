package hongsunghwi.todotaskscreening.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hongsunghwi.todotaskscreening.R
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme
import hongsunghwi.todotaskscreening.ui.theme.advancedShadow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodoCompleteItem(
    text: String,
    regDateTime: LocalDateTime,
    completeDateTime: LocalDateTime?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .advancedShadow(
                alpha = 0.25f,
                cornersRadius = 12.dp
            )
            .background(
                color = TodoTaskScreeningTheme.colors.white,
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = text,
            style = TodoTaskScreeningTheme.typography.bodyRegular2
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(
                    R.string.todo_complete_item_reg_date,
                    DateTimeFormatter.ofPattern("yyyy/MM/dd").format(regDateTime)
                ),
                style = TodoTaskScreeningTheme.typography.labelRegular
            )
            Text(
                text = completeDateTime?.let {
                    stringResource(
                        R.string.todo_complete_item_complete_date,
                        DateTimeFormatter.ofPattern("yyyy/MM/dd").format(it)
                    )
                } ?: "-",
                style = TodoTaskScreeningTheme.typography.labelRegular
            )
        }
    }
}

@Preview
@Composable
private fun TodoCompleteItemPreview() {
    TodoTaskScreeningTheme {
        TodoCompleteItem(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            regDateTime = LocalDateTime.now(),
            completeDateTime = LocalDateTime.now()
        )
    }
}