package hongsunghwi.todotaskscreening.ui.screen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import hongsunghwi.todotaskscreening.presentation.event.HistorySideEffect
import hongsunghwi.todotaskscreening.presentation.event.HistoryUiEvent
import hongsunghwi.todotaskscreening.presentation.viewmodel.HistoryViewModel
import hongsunghwi.todotaskscreening.ui.component.CommonAppBar
import hongsunghwi.todotaskscreening.ui.component.NavigationIconButton
import hongsunghwi.todotaskscreening.ui.component.TodoCompleteItem
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme
import java.time.LocalDateTime

@Composable
fun HistoryRoute(
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val completedTodos by historyViewModel.completedTodosState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        historyViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                HistorySideEffect.OnBack -> onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    HistoryScreen(
        onBack = {
            historyViewModel.sendEvent(HistoryUiEvent.OnBack)
        },
        completedTodos = completedTodos
    )
}

@Composable
private fun HistoryScreen(
    onBack: () -> Unit,
    completedTodos: List<Todo>
) {
    Column(
        modifier = Modifier
            .background(TodoTaskScreeningTheme.colors.gray02)
            .fillMaxSize()
    ) {
        CommonAppBar(
            title = {
                Text(
                    text = stringResource(R.string.history_screen_title),
                    style = TodoTaskScreeningTheme.typography.titleBold
                )
            },
            navigationIcon = {
                NavigationIconButton(
                    onClick = onBack
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
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
                start = 14.5.dp,
                end = 14.5.dp,
                top = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = completedTodos,
                key = { it.id }
            ) { todo ->
                TodoCompleteItem(
                    text = todo.content,
                    regDateTime = todo.regDateTime,
                    completeDateTime = todo.completeDateTime
                )
            }
            item {
                Spacer(Modifier.navigationBarsPadding())
            }
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    val todos = List(5) {
        Todo(
            id = it,
            content = "Todo Test",
            regDateTime = LocalDateTime.now(),
            completeDateTime = LocalDateTime.now()
        )
    }

    TodoTaskScreeningTheme {
        HistoryScreen(
            onBack = {},
            completedTodos = todos
        )
    }
}