package il.co.gonisch.moviesphere.data


/**
 * Warper class to make it easier to change
 * the Composable ui according to the current data state
 * In the following cases.
 * @me - out keyword signals that this class can only produce type T
 * and not consume it so its safe to say the returned type will always be T
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}