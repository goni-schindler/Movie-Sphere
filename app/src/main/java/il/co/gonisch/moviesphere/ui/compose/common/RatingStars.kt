package il.co.gonisch.moviesphere.ui.compose.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
fun StarRatingRow(
    modifier: Modifier = Modifier,
    rating: Float,
    maxStars: Int = 5,
    starSize: Int = 24,
    ratingScale: Float = 10f,
    starFillColor: Color = Color.Yellow,
    starEmptyColor: Color = Color.Gray
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        val scaledRating = (rating / ratingScale) * maxStars
        val fullStars = min(scaledRating.toInt(), maxStars) // Number of full stars
        val hasHalfStar = (scaledRating - fullStars) >= 0.5 // Check if there's a half star

        // Draw full stars
        repeat(fullStars) {
            Star(color = starFillColor, starSize = starSize)
        }

        // Draw half star if applicable
        if (hasHalfStar) {
            HalfTintedStar(leftColor = starFillColor, rightColor = starEmptyColor)
        }

        // Draw empty stars to fill up the row
        val emptyStars = maxStars - fullStars - if (hasHalfStar) 1 else 0
        repeat(emptyStars) {
            Star(color = starEmptyColor, starSize = starSize)
        }
    }
}

@Composable
fun Star(color: Color, starSize: Int) {
    Icon(
        imageVector = Icons.Filled.Star,
        contentDescription = "Star Icon",
        tint = color,
        modifier = Modifier
            .size(starSize.dp)
            .testTag("StarIcon")
    )
}

@Composable
fun HalfTintedStar(
    leftColor: Color,
    rightColor: Color,
    size: Int = 24
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .testTag("HalfTintedStarIcon")
    ) {
        // Left half (Clipped)
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "StarIcon",
            tint = leftColor,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    clip = true
                    shape = GenericShape { size, _ ->
                        moveTo(0f, 0f)
                        lineTo(size.width / 2, 0f)
                        lineTo(size.width / 2, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                }
        )

        // Right half (Clipped)
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "StarIcon",
            tint = rightColor,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    clip = true
                    shape = GenericShape { size, _ ->
                        moveTo(size.width / 2, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height)
                        lineTo(size.width / 2, size.height)
                        close()
                    }
                }
        )
    }
}

@Preview
@Composable
fun HalfTintedStarPreview() {
    Row {
        HalfTintedStar(leftColor = Color.Red, rightColor = Color.Yellow, size = 48)
        Spacer(modifier = Modifier.width(8.dp))
        HalfTintedStar(leftColor = Color.Blue, rightColor = Color.Green, size = 48)
    }
}

@Preview
@Composable
fun StarRatingRowPreview() {
    StarRatingRow(rating = 3.7f)
}


@Preview
@Composable
fun StarRatingRowPreviewLong() {
    StarRatingRow(rating = 7.7f, maxStars = 20)
}