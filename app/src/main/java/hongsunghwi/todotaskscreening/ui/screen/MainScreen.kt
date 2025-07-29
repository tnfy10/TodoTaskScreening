package hongsunghwi.todotaskscreening.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hongsunghwi.todotaskscreening.R
import hongsunghwi.todotaskscreening.ui.component.CommonAppBar
import hongsunghwi.todotaskscreening.ui.component.NavigationIconButton
import hongsunghwi.todotaskscreening.ui.component.TodoItem
import hongsunghwi.todotaskscreening.ui.component.TodoTextField
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme

@Composable
fun MainRoute(
    onNavigateToHistory: () -> Unit
) {
    MainScreen(
        onClickHistory = onNavigateToHistory,
        onClickTodoComplete = {},
        onClickTodoDelete = {},
        todoValue = "",
        onTodoValueChange = {},
        onClickConfirm = {}
    )
}

@Composable
private fun MainScreen(
    onClickHistory: () -> Unit,
    onClickTodoComplete: (id: Int) -> Unit,
    onClickTodoDelete: (id: Int) -> Unit,
    todoValue: String,
    onTodoValueChange: (String) -> Unit,
    onClickConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(TodoTaskScreeningTheme.colors.gray02)
            .fillMaxSize()
            .imePadding()
    ) {
        CommonAppBar(
            title = {
                Text(
                    text = stringResource(R.string.main_screen_title),
                    style = TodoTaskScreeningTheme.typography.titleBold
                )
            },
            actions = {
                NavigationIconButton(
                    onClick = onClickHistory
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_history),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 14.5.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(30) {
                TodoItem(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                    onClickComplete = {
                        onClickTodoComplete(it)
                    },
                    onClickDelete = {
                        onClickTodoDelete(it)
                    }
                )
            }
        }
        TodoTextField(
            value = todoValue,
            onValueChange = onTodoValueChange,
            onClickConfirm = onClickConfirm
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    TodoTaskScreeningTheme {
        MainScreen(
            onClickHistory = {},
            onClickTodoComplete = {},
            onClickTodoDelete = {},
            todoValue = "",
            onTodoValueChange = {},
            onClickConfirm = {}
        )
    }
}