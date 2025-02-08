package il.co.gonisch.moviesphere.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.gonisch.moviesphere.data.Genre
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.data.MoviesRepository
import il.co.gonisch.moviesphere.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresScreenViewModel
@Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    /**
     * StateFlow is a hot flow meaning it will always hold a value and starts
     * before it is being collected
     */
    private val _genres = MutableStateFlow<UiState<List<Genre>>>(UiState.Loading)
    val genres: StateFlow<UiState<List<Genre>>> = _genres.asStateFlow()

    init {
        fetchGenres()
    }

    /**
     * .catch is a Flow operator, same as try-catch basically
     */
    private fun fetchGenres() {
        viewModelScope.launch {
            moviesRepository.getGenresFlow()
                .catch {
                    _genres.value = UiState.Error("Error fetching genres")
                }
                .collect {
                    _genres.value = UiState.Success(it)
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