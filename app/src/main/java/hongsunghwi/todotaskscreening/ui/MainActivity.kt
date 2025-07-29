package hongsunghwi.todotaskscreening.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hongsunghwi.todotaskscreening.ui.screen.MainRoute
import hongsunghwi.todotaskscreening.ui.theme.TodoTaskScreeningTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTaskScreeningTheme {
                val navController = rememberNavController()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = MainRoute
                    ) {
                        composable<MainRoute> {
                            MainRoute()
                        }
                    }
                }
            }
        }
    }
}