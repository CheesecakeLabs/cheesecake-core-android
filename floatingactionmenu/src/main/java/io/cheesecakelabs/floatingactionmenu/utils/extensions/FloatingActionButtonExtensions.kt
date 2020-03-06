package io.cheesecakelabs.floatingactionmenu.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.cheesecakelabs.core.utils.extensions.getContextCompatColor

fun FloatingActionButton.setColors(context: Context, backgroundColor: Int, foregroundColor: Int) {
    backgroundTintList = ColorStateList.valueOf(context.getContextCompatColor(backgroundColor))

    with(ColorStateList.valueOf(context.getContextCompatColor(foregroundColor))) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            imageTintList = this
        } else {
            foregroundTintList = this
        }
    }
}

fun FloatingActionButton.setForegroundDrawable(drawable: Drawable?) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        scaleType = ImageView.ScaleType.CENTER
        setImageDrawable(drawable)
    } else {
        foreground = drawable
    }
}