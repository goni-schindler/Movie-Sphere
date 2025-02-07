package il.co.gonisch.moviesphere.data


sealed class UiState<out T> {
    data object Loading : UiState<Nothing>() // Represents loading state
    data class Success<T>(val data: T) : UiState<T>() // Represents success state
    data class Error(val message: String) : UiState<Nothing>() // Represents error state
}