package hongsunghwi.todotaskscreening.presentation.event

sealed class MainUiEvent {
    data object AddTodo : MainUiEvent()
    data class OnTodoValueChange(val value: String) : MainUiEvent()
    data object OnClickHistory : MainUiEvent()
    data class OnClickDelete(val id: Int) : MainUiEvent()
}