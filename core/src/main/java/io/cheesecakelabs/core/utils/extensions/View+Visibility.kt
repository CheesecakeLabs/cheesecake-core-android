package io.cheesecakelabs.core.utils.extensions

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

val View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE
