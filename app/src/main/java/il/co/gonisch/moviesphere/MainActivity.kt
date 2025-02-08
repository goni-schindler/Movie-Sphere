package il.co.gonisch.moviesphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import il.co.gonisch.moviesphere.ui.compose.MovieSphereApp
import il.co.gonisch.moviesphere.ui.theme.MovieSphereTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieSphereTheme {
                val systemUiController = rememberSystemUiController()
                // Disable Material Theme from affecting the status bar
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = false
                )
                MovieSphereApp()
            }
        }
    }
}
