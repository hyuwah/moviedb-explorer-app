package dev.hyuwah.moviedbexplorer.presentation.utils

import android.view.View

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisibleIf(predicate: Boolean) {
    visibility = if (predicate) View.VISIBLE else View.GONE
}