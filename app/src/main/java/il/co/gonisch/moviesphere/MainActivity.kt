package il.co.gonisch.moviesphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                MovieSphereApp()
            }
        }
    }
}
