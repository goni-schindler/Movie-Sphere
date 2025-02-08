package il.co.gonisch.moviesphere.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import il.co.gonisch.moviesphere.api.TmdbApi
import retrofit2.HttpException
import java.io.IOException

/**
 * This class uses as a factory for defining
 * how new movies pages should be retrieved from the api
 */
class TmdbApiPagingSource(
    private val api: TmdbApi,
    private val genreId: Int
) : PagingSource<Int, Movie>() {

    /**
     * @see PagingState stores the current state of the paging source
     */
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            // Fetch the data for the current page
            val response = api.getMoviesByGenreId(genreId = genreId, page = page)

            // Handle successful response
            if (response.isSuccessful) {
                val movies = response.body()?.movies ?: emptyList()
                val nextPage = response.body()?.run {
                    if (page < totalPages) page + 1 else null
                }
                LoadResult.Page(
                    data = movies,
                    prevKey = null,  // No need to worry about the previous page
                    nextKey = nextPage  // Pass the next page number
                )
            } else {
                // Handle API error response
                LoadResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            // Handle network error
            LoadResult.Error(e)
        }
    }
}