package dev.hyuwah.moviedbexplorer.presentation.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.setVisibleIf(predicate: Boolean) {
    visibility = if (predicate) View.VISIBLE else View.GONE
}

/// Taken from https://gist.github.com/brescia123/d315af5bd3f4a47c5d548cd9c7de55eb
/** Set the View visibility to VISIBLE and eventually animate the View alpha till 100% */
fun View.setVisible(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

/** Set the View visibility to INVISIBLE and eventually animate view alpha till 0% */
fun View.setInvisible(animate: Boolean = true) {
    hide(View.INVISIBLE, animate)
}

/** Set the View visibility to GONE and eventually animate view alpha till 0% */
fun View.setGone(animate: Boolean = true) {
    hide(View.GONE, animate)
}

/** Convenient method that chooses between View.visible() or View.invisible() methods */
fun View.setVisibleOrInvisible(show: Boolean, animate: Boolean = true) {
    if (show) setVisible(animate) else setInvisible(animate)
}

/** Convenient method that chooses between View.visible() or View.gone() methods */
fun View.setVisibleOrGone(show: Boolean, animate: Boolean = true) {
    if (show) setVisible(animate) else setGone(animate)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}