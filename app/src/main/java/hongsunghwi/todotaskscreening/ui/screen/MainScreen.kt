package hongsunghwi.todotaskscreening.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme

@Composable
fun MainRoute() {
    MainScreen()
}

@Composable
private fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("메인 화면")
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    TodoTaskScreeningTheme {
        MainScreen()
    }
}