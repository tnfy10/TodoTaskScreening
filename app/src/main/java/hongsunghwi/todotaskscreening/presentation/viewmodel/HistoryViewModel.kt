package hongsunghwi.todotaskscreening.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hongsunghwi.todotaskscreening.domain.usecase.GetCompletedTodoUseCase
import hongsunghwi.todotaskscreening.presentation.event.HistorySideEffect
import hongsunghwi.todotaskscreening.presentation.event.HistoryUiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getCompletedTodoUseCase: GetCompletedTodoUseCase
): ViewModel() {
    private val _sideEffects = Channel<HistorySideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    val completedTodosState = getCompletedTodoUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun sendEvent(event: HistoryUiEvent) {
        viewModelScope.launch {
            when (event) {
                HistoryUiEvent.OnBack -> {
                    _sideEffects.send(HistorySideEffect.OnBack)
                }
            }
        }
    }
}