package hongsunghwi.todotaskscreening.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val ColorPalette = TodoColors(
    white = White,
    black = Black,
    red = Red,
    gray01 = Gray01,
    gray02 = Gray02,
    gray03 = Gray03
)

val LocalTodoColors = staticCompositionLocalOf<TodoColors> {
    error("No TodoColorPalette provided")
}

@Composable
fun TodoTaskScreeningTheme(
    content: @Composable () -> Unit
) {
    val colorPalette = remember { ColorPalette }

    CompositionLocalProvider(
        LocalTypography provides Typography,
        LocalTodoColors provides colorPalette
    ) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                background = colorPalette.white
            ),
            content = content
        )
    }
}

object TodoTaskScreeningTheme {
    val colors: TodoColors
        @Composable
        get() = LocalTodoColors.current
    val typography: TodoTypography
        @Composable
        get() = LocalTypography.current
}

@Stable
class TodoColors(
    white: Color,
    black: Color,
    red: Color,
    gray01: Color,
    gray02: Color,
    gray03: Color
) {
    var white: Color by mutableStateOf(white)
        private set
    var black: Color by mutableStateOf(black)
        private set
    var red: Color by mutableStateOf(red)
        private set
    var gray01: Color by mutableStateOf(gray01)
        private set
    var gray02: Color by mutableStateOf(gray02)
        private set
    var gray03: Color by mutableStateOf(gray03)
        private set

    fun update(other: TodoColors) {
        white = other.white
        black = other.black
        red = other.red
        gray01 = other.gray01
        gray02 = other.gray02
        gray03 = other.gray03
    }

    fun copy(): TodoColors = TodoColors(
        white = white,
        black = black,
        red = red,
        gray01 = gray01,
        gray02 = gray02,
        gray03 = gray03
    )
}