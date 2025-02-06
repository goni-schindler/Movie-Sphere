package il.co.gonisch.moviesphere.ui.compose.genres

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import il.co.gonisch.moviesphere.data.MoviePoster
import il.co.gonisch.moviesphere.ui.compose.common.MoviesPreviewProvider
import java.time.LocalDate

@Composable
fun MoviesGridView(movies: List<MoviePoster>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(count = movies.size) { index ->
            MovieCardView(moviePoster = movies[index])
        }
    }
}

@Preview
@Composable
fun MoviesGridViewPreview(
    @PreviewParameter(MoviesPreviewProvider::class) movies: List<MoviePoster>
) {
    MoviesGridView(movies = movies)
}