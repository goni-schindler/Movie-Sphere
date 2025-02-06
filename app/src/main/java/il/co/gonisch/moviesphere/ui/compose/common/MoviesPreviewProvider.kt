package il.co.gonisch.moviesphere.ui.compose.common

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import il.co.gonisch.moviesphere.data.MoviePoster
import java.time.LocalDate

class MoviesPreviewProvider : PreviewParameterProvider<List<MoviePoster>> {
    override val values: Sequence<List<MoviePoster>> =
        sequenceOf(
            listOf(
                MoviePoster(
                    title = "Sonic the hedhoge",
                    releaseDate = LocalDate.now(),
                    posterUrl = "https://image.tmdb.org/t/p/w500//d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                    voteAverage = 1.7
                ),
                MoviePoster(
                    title = "Sonic the hedhoge",
                    releaseDate = LocalDate.now(),
                    posterUrl = "https://image.tmdb.org/t/p/w500//d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                    voteAverage = 1.7
                ),
                MoviePoster(
                    title = "Sonic the hedhoge",
                    releaseDate = LocalDate.now(),
                    posterUrl = "https://image.tmdb.org/t/p/w500//d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                    voteAverage = 1.7
                ),
                MoviePoster(
                    title = "Sonic the hedhoge",
                    releaseDate = LocalDate.now(),
                    posterUrl = "https://image.tmdb.org/t/p/w500//d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                    voteAverage = 1.7
                )
            )
        )

}