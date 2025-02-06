package il.co.gonisch.moviesphere.api

import il.co.gonisch.moviesphere.BuildConfig
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TmdbApiTest {
    private lateinit var tmdbApi: TmdbApi

    @Before
    fun setUp() {
        // Initialize the real Retrofit instance for integration testing
        tmdbApi = TmdbApi.create()
    }

    @Test
    fun getGenresTest() = runBlocking {
        try {
            val response = tmdbApi.getGenres(BuildConfig.TMDB_API_KEY)
            assertNotNull(response)
            assertTrue(response.genres.isNotEmpty())
        } catch (e: Exception) {
            println("Api call error: ${e.message}")
            assertNull(e) // fail test
        }
    }

    @Test
    fun getMoviesByGenreIdTest() = runBlocking {
        try {
            val response = tmdbApi.getMoviesByGenreId(BuildConfig.TMDB_API_KEY, 28)
            assertNotNull(response)
            assertTrue(response.movies.isNotEmpty())
        } catch (e: Exception) {
            println("Api call error: ${e.message}")
            assertNull(e) // fail test
        }
    }
}