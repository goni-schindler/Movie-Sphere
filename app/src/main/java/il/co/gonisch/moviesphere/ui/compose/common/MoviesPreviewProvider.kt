package il.co.gonisch.moviesphere.ui.compose.common

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.data.MoviePoster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

class MoviesPreviewProvider : PreviewParameterProvider<List<Movie>> {
    override val values: Sequence<List<Movie>> =
        sequenceOf(
            listOf(
                Movie(
                    title = "Sonic the hedhoge",
                    releaseDate = LocalDate.now(),
                    posterPath = "https://image.tmdb.org/t/p/w500//d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                    voteAverage = 1.7
                )

            )
        )

}

class MoviesPagingDataPreviewProvider : PreviewParameterProvider<PagingData<Movie>> {
    override val values: Sequence<PagingData<Movie>> =
        sequenceOf(
            PagingData.from(
                listOf(
                    Movie(
                        title = "Sonic the hedhoge",
                        releaseDate = LocalDate.now(),
                        posterPath = "https://image.tmdb.org/t/p/w500//d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                        voteAverage = 1.7
                    )
                )
            )
        )
}