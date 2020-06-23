package dev.hyuwah.moviedbexplorer.presentation.utils

/**
 * Taken from
 * https://android.jlelse.eu/architecture-components-easy-mapping-of-actions-and-ui-state-207663e3fdd
 */
sealed class Action {
    object Load : Action()
    object Refresh : Action()
    object Retry : Action()
}