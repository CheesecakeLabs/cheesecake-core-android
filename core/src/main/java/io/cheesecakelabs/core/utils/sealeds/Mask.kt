package io.cheesecakelabs.core.utils.sealeds

import android.widget.EditText
import io.cheesecakelabs.core.utils.enums.CellPhoneMaskType
import io.cheesecakelabs.core.utils.extensions.mask

sealed class Mask {

    abstract val mask: String
    abstract val filter: Regex

    open fun buildTextWatcher(editText: EditText) {
        editText.mask(mask, filter)
    }

    object CPFMask: Mask() {
        override val mask: String
            get() = "###.###.###-##"
        override val filter: Regex
            get() = "[.\\-]".toRegex()
    }

    object CEPMask: Mask() {
        override val mask: String
            get() = "#####-###"
        override val filter: Regex
            get() = "[\\-]".toRegex()
    }

    object CreditCardMask: Mask() {
        override val mask: String
            get() = "#### #### #### ####"
        override val filter: Regex
            get() = "[ ]".toRegex()
    }

    class CellPhoneMask(private val cellPhoneMaskType: CellPhoneMaskType): Mask() {
        override val mask: String
            get() = cellPhoneMaskType.mask
        override val filter: Regex
            get() = "[+ ()\\-]".toRegex()
    }
}