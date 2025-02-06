package il.co.gonisch.moviesphere.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import il.co.gonisch.moviesphere.api.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: TmdbApi) {
    fun getGenresFlow(): Flow<Response<GetGenresResponse>> = flow {
        val response = api.getGenres()
        emit(response)
    }.flowOn(Dispatchers.IO)//make sure the api call is done on the right thread

    fun getMoviesByGenreIdFlow(genreId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = MAX_MOVIES_PER_AGE,  // Items per page
            enablePlaceholders = false,  // Don't use placeholders for missing data
            prefetchDistance = 2  // Fetch next few items in advance
        ),
        pagingSourceFactory = { TmdbApiPagingSource(api, genreId) }  // Provide the PagingSource
    ).flow.flowOn(Dispatchers.IO)

    companion object {
        const val MAX_MOVIES_PER_AGE = 20
    }
}