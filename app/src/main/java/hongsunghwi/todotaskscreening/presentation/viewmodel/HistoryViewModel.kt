package hongsunghwi.todotaskscreening.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hongsunghwi.todotaskscreening.presentation.event.HistorySideEffect
import hongsunghwi.todotaskscreening.presentation.event.HistoryUiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(

): ViewModel() {
    private val _sideEffects = Channel<HistorySideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

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