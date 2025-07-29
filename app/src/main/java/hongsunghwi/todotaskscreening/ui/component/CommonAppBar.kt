package hongsunghwi.todotaskscreening.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hongsunghwi.todotaskscreening.R
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme
import hongsunghwi.todotaskscreening.ui.theme.TopAppBarHeight

@Composable
fun CommonAppBar(
    title: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    titleAlignment: Alignment = Alignment.Center,
    containerColor: Color = TodoTaskScreeningTheme.colors.white,
    appBarHeight: Dp = TopAppBarHeight,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .background(
                color = containerColor
            )
            .statusBarsPadding()
            .fillMaxWidth()
            .height(appBarHeight)
            .padding(horizontal = 12.5.dp)
    ) {
        if (navigationIcon != null) {
            Box(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                navigationIcon()
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .height(appBarHeight),
            contentAlignment = titleAlignment,
            content = title
        )
        if (actions != null) {
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                actions()
            }
        }
    }
}

@Preview
@Composable
private fun CommonAppBarPreview() {
    TodoTaskScreeningTheme {
        CommonAppBar(
            title = {
                Text(
                    text = "title",
                    style = TodoTaskScreeningTheme.typography.titleBold
                )
            },
            navigationIcon = {
                NavigationIconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            actions = {
                NavigationIconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_history),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        )
    }
}