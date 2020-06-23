package dev.hyuwah.moviedbexplorer.presentation.utils

/**
 * Taken from
 * https://android.jlelse.eu/architecture-components-easy-mapping-of-actions-and-ui-state-207663e3fdd
 */
sealed class UIState<out R> {
    object Loading : UIState<Nothing>()
    object Retrying : UIState<Nothing>()
    object Refreshing : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Failure(val exception: Exception) : UIState<Nothing>()
    data class RefreshFailure(val exception: Exception) : UIState<Nothing>()
}