package io.cheesecakelabs.core.utils.helpers

import android.util.DisplayMetrics
import android.util.TypedValue

object DimensionsHelper {

    fun dpToFloat(dpQuantity: Float, displayMetrics: DisplayMetrics): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpQuantity, displayMetrics)
    }
}