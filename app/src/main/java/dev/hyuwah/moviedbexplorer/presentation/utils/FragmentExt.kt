package dev.hyuwah.moviedbexplorer.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

inline fun <IN : Any, OUT : Any> Fragment.observeStateOf(
    liveData: ActionStateLiveData<IN, OUT>,
    crossinline onChanged: (UIState<OUT?>) -> Unit
) {
    liveData.state.observe(viewLifecycleOwner) {
        onChanged(it)
    }
}