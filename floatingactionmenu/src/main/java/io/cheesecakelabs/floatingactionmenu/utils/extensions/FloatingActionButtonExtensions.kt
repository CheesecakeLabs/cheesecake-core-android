package io.cheesecakelabs.floatingactionmenu.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.cheesecakelabs.core.utils.extensions.getContextCompatColor

fun FloatingActionButton.setColors(context: Context, backgroundColor: Int, foregroundColor: Int) {
    backgroundTintList = ColorStateList.valueOf(context.getContextCompatColor(backgroundColor))
    foregroundTintList = ColorStateList.valueOf(context.getContextCompatColor(foregroundColor))
}