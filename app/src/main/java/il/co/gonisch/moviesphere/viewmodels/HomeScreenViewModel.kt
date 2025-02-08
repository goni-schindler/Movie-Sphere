package il.co.gonisch.moviesphere.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.gonisch.moviesphere.data.MoviesRepository
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun fetchMoviesByQuery(query: String) = moviesRepository.getMoviesByQuery(query)
}