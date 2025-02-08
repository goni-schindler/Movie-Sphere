package il.co.gonisch.moviesphere

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import il.co.gonisch.moviesphere.ui.compose.common.StarRatingRow
import org.junit.Rule
import org.junit.Test

class RatingStarsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `row-sanity`() {
        composeTestRule.setContent {
            StarRatingRow(rating = 10f, maxStars = 10, ratingScale = 10f)
        }
        composeTestRule.onAllNodesWithTag("StarIcon")
            .assertCountEquals(10)
    }

    @Test
    fun `half-star-tinted`() {
        composeTestRule.setContent {
            StarRatingRow(rating = 5.6f, maxStars = 10, ratingScale = 10f)
        }
        composeTestRule.onAllNodesWithTag("HalfTintedStarIcon")
            .assertCountEquals(1)
    }

    @Test
    fun `Rating-higher-than-max-stars`() {
        composeTestRule.setContent {
            StarRatingRow(rating = 11f, maxStars = 10, ratingScale = 10f)
        }
        composeTestRule.onAllNodesWithTag("StarIcon")
            .assertCountEquals(10)
    }

    @Test
    fun `scale-higher-than-max`() {
        composeTestRule.setContent {
            StarRatingRow(rating = 5f, maxStars = 5, ratingScale = 10f)
        }
        composeTestRule.onAllNodesWithTag("HalfTintedStarIcon")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithTag("StarIcon")
            .assertCountEquals(4) // each of half of the half-tinted is a star by itself
    }
}