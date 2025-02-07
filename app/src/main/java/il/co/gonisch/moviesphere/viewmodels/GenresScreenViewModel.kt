package il.co.gonisch.moviesphere.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.gonisch.moviesphere.data.Genre
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.data.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresScreenViewModel
@Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres.asStateFlow()

    private val _movies = MutableStateFlow<PagingData<Movie>?>(null)
    val movies: Flow<PagingData<Movie>> = _movies.filterNotNull()

    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            try {
                moviesRepository.getGenresFlow().collect {
                    _genres.value = it
                }
                fetchMoviesByGenreId(_genres.value[0].id) //TODO fetch first movies - unsafe!
            } catch (e: Exception) {
                Log.d("tiki", e.toString())
            }
        }
    }

    fun fetchMoviesByGenreId(genreId: Int) {
        viewModelScope.launch {
            moviesRepository.getMoviesByGenreIdFlow(genreId).collect {
                _movies.value = it
            }
        }
    }

}