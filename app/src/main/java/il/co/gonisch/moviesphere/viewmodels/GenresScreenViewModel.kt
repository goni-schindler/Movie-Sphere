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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresScreenViewModel
@Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres.asStateFlow()

    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            try {
                moviesRepository.getGenresFlow().collect {
                    _genres.value = it
                }
            } catch (e: Exception) {
                
            }
        }
    }

    fun fetchMoviesByGenreId(genreId: Int): StateFlow<PagingData<Movie>> =
        moviesRepository.getMoviesByGenreIdFlow(genreId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, // Only starts when collected
            initialValue = PagingData.empty() // Ensure an initial empty state
        )

}