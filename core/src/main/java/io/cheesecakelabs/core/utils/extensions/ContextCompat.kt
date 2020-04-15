package io.cheesecakelabs.core.utils.extensions

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import java.io.File

fun Context.getContextCompatColor(resourceId: Int): Int {
    return ContextCompat.getColor(this, resourceId)
}

fun Context.getColorStateList(resourceId: Int): ColorStateList? {
    return ContextCompat.getColorStateList(this, resourceId)
}

fun Context.getDataDir(): File? {
    return ContextCompat.getDataDir(this)
}

fun Context.getContextCompatDrawable(resourceId: Int): Drawable? {
    return ContextCompat.getDrawable(this, resourceId)
}

fun Context.checkSelfPermission(permission: String): Int {
    return ContextCompat.checkSelfPermission(this, permission)
}

fun Context.startActivities(intents: Array<Intent>): Boolean {
    return ContextCompat.startActivities(this, intents)
}

fun Context.startActivities(intents: Array<Intent>, options: Bundle): Boolean {
    return ContextCompat.startActivities(this, intents, options)
}