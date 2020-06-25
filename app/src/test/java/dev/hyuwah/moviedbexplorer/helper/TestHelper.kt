package dev.hyuwah.moviedbexplorer.helper

import androidx.lifecycle.Observer
import dev.hyuwah.moviedbexplorer.presentation.utils.UIState
import io.mockk.spyk

object TestHelper {
    fun <T> getMockStateObserver(): Observer<UIState<T>> = spyk(Observer { })
    fun <T> getMockObserver(): Observer<T> = spyk(Observer { })
}