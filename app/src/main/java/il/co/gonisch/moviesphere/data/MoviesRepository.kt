package il.co.gonisch.moviesphere.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import il.co.gonisch.moviesphere.api.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * this is the SSOT for retrieving data from the TMDB api.
 * @property getMoviesByGenreIdFlow this method utilize the PagingData
 * from android paging library.
 */
class MoviesRepository @Inject constructor(private val api: TmdbApi) {
    fun getGenresFlow(): Flow<List<Genre>> = flow {
        val genres = api.getGenres().body()?.genres?.toList() ?: emptyList()
        emit(genres)
    }.flowOn(Dispatchers.IO)//make sure the api call is done on the right thread

    fun getMoviesByGenreIdFlow(genreId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = MAX_MOVIES_PER_AGE,  // Items per page
            enablePlaceholders = false,  // Don't use placeholders for missing data
            prefetchDistance = 2  // Fetch next 2 pages in advance
        ),
        pagingSourceFactory = { TmdbApiPagingSource(api, genreId) }  // Provide the PagingSource
    ).flow.flowOn(Dispatchers.IO)

    fun getMoviesByQuery(query: String): Flow<List<Movie>> = flow {
        val movies = api.getMoviesByQuery(query).body()?.movies?.toList() ?: emptyList()
        emit(movies)
    }.flowOn(Dispatchers.IO)

    companion object {
        //No official docs about it in the Tmdb Api so use this constant instead
        const val MAX_MOVIES_PER_AGE = 20
    }

}