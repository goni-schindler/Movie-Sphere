package il.co.gonisch.moviesphere

import il.co.gonisch.moviesphere.api.TmdbApi
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
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
    fun getGenresTest() = runTest {
        try {
            val response = tmdbApi.getGenres(BuildConfig.TMDB_API_KEY)
            assertNotNull(response)
            assertTrue(response.body()?.genres?.isNotEmpty() ?: false)
        } catch (e: Exception) {
            println("Api call error: ${e.message}")
            assertNull(e) // fail test
        }
    }

    @Test
    fun getMoviesByGenreIdTest() = runTest {
        try {
            val response = tmdbApi.getMoviesByGenreId(BuildConfig.TMDB_API_KEY, 28)
            assertNotNull(response)
            assertTrue(response.body()?.movies?.isNotEmpty() ?: false)
        } catch (e: Exception) {
            println("Api call error: ${e.message}")
            assertNull(e) // fail test
        }
    }
}