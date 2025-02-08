package il.co.gonisch.moviesphere

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import il.co.gonisch.moviesphere.data.Genre
import il.co.gonisch.moviesphere.ui.compose.screen.genres.GenresScreen
import il.co.gonisch.moviesphere.ui.theme.MovieSphereTheme
import org.junit.Rule
import org.junit.Test

class GenresScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sanityTest() {
        composeTestRule.setContent {
            MovieSphereTheme {
                GenresScreen(
                    genres = emptyList()
                )
            }
        }
    }

    @Test
    fun displayGenre() {
        composeTestRule.setContent {
            GenresScreen(
                genres = listOf(
                    Genre(1, "testGenre")
                )
            )
        }
        composeTestRule.onNodeWithText("testGenre").assertIsDisplayed()
    }
}