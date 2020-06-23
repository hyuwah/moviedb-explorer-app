package dev.hyuwah.moviedbexplorer.presentation.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

/**
 * Taken from
 * https://android.jlelse.eu/architecture-components-easy-mapping-of-actions-and-ui-state-207663e3fdd
 */
class ActionStateLiveData<T>(
    private val coroutineContext: CoroutineContext,
    fetchData: (suspend () -> Response<T>)
) {
    private val action = MutableLiveData<Action>()
    private var data: T? = null // backing data

    val state = action.switchMap {
        liveData(context = coroutineContext) {
            when (action.value) {
                Action.Load -> {
                    emit(UIState.Loading)
                }

                Action.Refresh -> {
                    emit(UIState.Refreshing)
                }

                Action.Retry -> {
                    emit(UIState.Retrying)
                }
            }

            try {
                val response = fetchData()
                val body = response.body()
                when {
                    response.isSuccessful && body != null -> {
                        data = body
                        emit(UIState.Success(body))
                    }
                    action.value == Action.Refresh -> {
                        emit(UIState.RefreshFailure(Exception()))
                    }
                    else -> {
                        emit(UIState.Failure(Exception()))
                    }
                }
            } catch (exception: Exception) {
                if (action.value == Action.Refresh) {
                    emit(UIState.RefreshFailure(Exception()))
                    data?.let {
                        // emit success with existing data
                        emit(UIState.Success<T>(it))
                    }
                }
                else {
                    emit(UIState.Failure(Exception()))
                }
            }
        }
    }

    // Helpers for triggering different actions

    fun retry() {
        action.value = Action.Retry
    }

    fun refresh() {
        action.value = Action.Refresh
    }

    fun load() {
        action.value = Action.Load
    }
}