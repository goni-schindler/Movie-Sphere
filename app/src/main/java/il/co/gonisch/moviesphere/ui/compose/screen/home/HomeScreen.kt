package il.co.gonisch.moviesphere.ui.compose.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import il.co.gonisch.moviesphere.R
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.ui.compose.screen.genres.MovieCardView
import il.co.gonisch.moviesphere.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    HomeScreen { query -> viewModel.fetchMoviesByQuery(query) }
}

@Composable
fun HomeScreen(
    moviesProvider: (String) -> Flow<List<Movie>>
) {
    var searchquery by remember { mutableStateOf("") }
    var showKonffeti by remember { mutableStateOf(false) }
    val movies by moviesProvider(searchquery).collectAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(45.dp)),
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = ""
                )
            },
            placeholder = { Text(stringResource(id = R.string.search_bar_placeholder)) },
            value = searchquery,
            onValueChange = {
                if (it == "shutterfly")
                    showKonffeti = true
                searchquery = it
            })
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2))
            {
                items(count = movies.size) { index ->
                    MovieCardView(movie = movies[index])
                }
            }
            if (showKonffeti) {
                KonfettiView(
                    modifier = Modifier.fillMaxSize(),
                    parties = listOf(
                        Party(
                            emitter = Emitter(duration = 50, TimeUnit.SECONDS).perSecond(30)
                        )
                    ),
                )
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(moviesProvider = { flowOf() })
}