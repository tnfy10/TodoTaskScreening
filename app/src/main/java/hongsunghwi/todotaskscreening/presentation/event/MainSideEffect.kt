package hongsunghwi.todotaskscreening.presentation.event

sealed class MainSideEffect {
    data object NavigateToHistory : MainSideEffect()
}