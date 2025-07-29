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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hongsunghwi.todotaskscreening.R
import hongsunghwi.todotaskscreening.domain.model.Todo
import hongsunghwi.todotaskscreening.presentation.event.MainSideEffect
import hongsunghwi.todotaskscreening.presentation.event.MainUiEvent
import hongsunghwi.todotaskscreening.presentation.viewmodel.MainViewModel
import hongsunghwi.todotaskscreening.ui.component.CommonAppBar
import hongsunghwi.todotaskscreening.ui.component.NavigationIconButton
import hongsunghwi.todotaskscreening.ui.component.TodoItem
import hongsunghwi.todotaskscreening.ui.component.TodoTextField
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme
import java.time.LocalDateTime

@Composable
fun MainRoute(
    mainViewModel: MainViewModel = hiltViewModel(),
    onNavigateToHistory: () -> Unit
) {
    val todos by mainViewModel.todosState.collectAsStateWithLifecycle()
    val todoValue by mainViewModel.todoValueState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        mainViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                MainSideEffect.NavigateToHistory -> onNavigateToHistory()
            }
        }
    }

    MainScreen(
        onClickHistory = {
            mainViewModel.sendEvent(MainUiEvent.OnClickHistory)
        },
        todos = todos,
        onClickTodoComplete = {
            mainViewModel.sendEvent(MainUiEvent.OnClickComplete(it))
        },
        onClickTodoDelete = {
            mainViewModel.sendEvent(MainUiEvent.OnClickDelete(it))
        },
        todoValue = todoValue,
        onTodoValueChange = {
            mainViewModel.sendEvent(MainUiEvent.OnTodoValueChange(it))
        },
        onClickConfirm = {
            mainViewModel.sendEvent(MainUiEvent.AddTodo)
        }
    )
}

@Composable
private fun MainScreen(
    onClickHistory: () -> Unit,
    todos: List<Todo>,
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
            items(
                items = todos,
                key = { it.id }
            ) { todo ->
                TodoItem(
                    text = todo.content,
                    onClickComplete = {
                        onClickTodoComplete(todo.id)
                    },
                    onClickDelete = {
                        onClickTodoDelete(todo.id)
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

@Preview
@Composable
private fun MainScreenPreview() {
    val todos = List(5) {
        Todo(
            id = it,
            content = "Todo Test",
            regDateTime = LocalDateTime.now(),
            completeDateTime = null
        )
    }

    TodoTaskScreeningTheme {
        MainScreen(
            onClickHistory = {},
            todos = todos,
            onClickTodoComplete = {},
            onClickTodoDelete = {},
            todoValue = "Todo Test",
            onTodoValueChange = {},
            onClickConfirm = {}
        )
    }
}