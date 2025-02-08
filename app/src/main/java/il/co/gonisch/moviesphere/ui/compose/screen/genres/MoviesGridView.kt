package il.co.gonisch.moviesphere.ui.compose.screen.genres

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.ui.compose.common.MoviesPagingDataPreviewProvider
import kotlinx.coroutines.flow.flowOf

@Composable
fun MoviesGridView(movies: LazyPagingItems<Movie>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2)
    ) {
        items(count = movies.itemCount) { index ->
            movies[index]?.let { MovieCardView(movie = it) }
        }
    }
}

@Preview
@Composable
fun MoviesGridViewPreview(
    @PreviewParameter(MoviesPagingDataPreviewProvider::class) movies: PagingData<Movie>
) {
    //** flowOf creates a static flow, NO async in Preview!!
    val lazyMovies = flowOf(movies).collectAsLazyPagingItems()
    MoviesGridView(movies = lazyMovies)
}