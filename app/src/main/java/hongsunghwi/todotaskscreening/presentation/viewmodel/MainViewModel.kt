package hongsunghwi.todotaskscreening.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hongsunghwi.todotaskscreening.domain.repository.TodoRepository
import hongsunghwi.todotaskscreening.domain.usecase.AddTodoUseCase
import hongsunghwi.todotaskscreening.domain.usecase.GetTodosUseCase
import hongsunghwi.todotaskscreening.presentation.event.MainSideEffect
import hongsunghwi.todotaskscreening.presentation.event.MainUiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val todoRepository: TodoRepository
) : ViewModel() {
    val todosState = getTodosUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _todoValueState = MutableStateFlow("")
    val todoValueState = _todoValueState.asStateFlow()

    private val _sideEffects = Channel<MainSideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    fun sendEvent(event: MainUiEvent) {
        viewModelScope.launch {
            when (event) {
                is MainUiEvent.AddTodo -> addTodo()
                is MainUiEvent.OnTodoValueChange -> {
                    _todoValueState.value = event.value
                }

                MainUiEvent.OnClickHistory -> {
                    _sideEffects.send(MainSideEffect.NavigateToHistory)
                }

                is MainUiEvent.OnClickDelete -> {
                    todoRepository.deleteTodo(event.id)
                }
            }
        }
    }

    private fun addTodo() {
        viewModelScope.launch {
            addTodoUseCase(todoValueState.value)
            _todoValueState.value = ""
        }
    }
}