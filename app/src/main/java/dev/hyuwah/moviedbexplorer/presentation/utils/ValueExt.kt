package dev.hyuwah.moviedbexplorer.presentation.utils

fun String?.orIfEmpty(value: String): String {
    return this.orEmpty().ifEmpty { value }
}