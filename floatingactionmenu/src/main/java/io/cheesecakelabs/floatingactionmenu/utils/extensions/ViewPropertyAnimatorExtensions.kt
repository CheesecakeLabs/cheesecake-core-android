package io.cheesecakelabs.floatingactionmenu.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.setupListener(onAnimationStart: (() -> Unit) = {}, onAnimationEnd: (() -> Unit) = {}) {
    setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animator: Animator) {
            onAnimationStart.invoke()
        }

        override fun onAnimationEnd(animator: Animator) {
            onAnimationEnd.invoke()
        }
    })
}