package il.co.gonisch.moviesphere.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import il.co.gonisch.moviesphere.ui.compose.genres.GenresScreen


@Composable
fun MovieSphereApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieSphereTopAppBar(modifier = Modifier)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            GenresScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieSphereTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Movie Sphere",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier,
        actions = {
            IconButton(
                { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        },
    )
}
