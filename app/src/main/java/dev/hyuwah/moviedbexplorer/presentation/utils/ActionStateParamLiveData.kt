package dev.hyuwah.moviedbexplorer.presentation.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.Mapper
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

/**
 * Taken from
 * https://android.jlelse.eu/architecture-components-easy-mapping-of-actions-and-ui-state-207663e3fdd
 */
class ActionStateParamLiveData<IN : Any, PARAM : Any, OUT : Any>(
    private val coroutineScope: CoroutineScope,
    private val mapper: Mapper<IN, OUT>,
    fetchData: (suspend (PARAM) -> Response<IN>)
) {
    private val action = MutableLiveData<Action>()
    private var data: OUT? = null // backing data
    private var param: PARAM? = null

    val state = action.switchMap {
        liveData(context = coroutineScope.coroutineContext) {
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
                if (param != null) {
                    val response = fetchData(param!!)
                    val body = response.body()
                    when {
                        response.isSuccessful && body != null -> {
                            data = mapper.map(body)
                            emit(UIState.Success(data))
                        }
                        action.value == Action.Refresh -> {
                            emit(UIState.RefreshFailure(Exception()))
                        }
                        else -> {
                            emit(UIState.Failure(Exception()))
                        }
                    }
                } else {
                    throw IllegalStateException("Param is null")
                }
            } catch (exception: Exception) {
                if (action.value == Action.Refresh) {
                    emit(UIState.RefreshFailure(Exception()))
                    data?.let {
                        // emit success with existing data
                        emit(UIState.Success<OUT>(it))
                    }
                } else {
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

    fun load(param: PARAM) {
        this.param = param
        action.value = Action.Load
    }
}