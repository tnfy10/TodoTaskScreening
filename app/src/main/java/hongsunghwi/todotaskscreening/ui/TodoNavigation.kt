package hongsunghwi.todotaskscreening.ui

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object MainRoute

@Serializable
object HistoryRoute

fun NavController.navigateToHistory() {
    navigate(HistoryRoute)
}