package il.co.gonisch.moviesphere.ui.compose.genres

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import il.co.gonisch.moviesphere.R
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.data.MoviePoster
import il.co.gonisch.moviesphere.ui.compose.common.MoviesPreviewProvider
import il.co.gonisch.moviesphere.ui.compose.common.StarRatingRow
import il.co.gonisch.moviesphere.ui.theme.MovieSphereTheme
import java.time.LocalDate
import java.util.Date

@Composable
fun MovieCardView(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            model = "https://image.tmdb.org/t/p/w154" + movie.posterPath,
            contentDescription = null,
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp),
            text = movie.title,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp),
            text = "(${movie.releaseDate.year})",
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )
        StarRatingRow(
            modifier = Modifier.padding(7.dp),
            rating = movie.voteAverage.toFloat(),
            starFillColor = Color.Green
        )
    }
}

@Composable
@Preview
fun MovieCardViewPreview(
    @PreviewParameter(MoviesPreviewProvider::class) movies: List<Movie>
) {
    MovieSphereTheme {
        MovieCardView(
            movie = movies[0]
        )
    }
}