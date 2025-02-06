package il.co.gonisch.moviesphere.data

import java.time.LocalDate

data class MoviePoster(
    var releaseDate: LocalDate,
    var title: String,
    var posterUrl: String,
    var voteAverage: Double,
)
