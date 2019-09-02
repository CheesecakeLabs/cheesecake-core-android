package io.cheesecakelabs.core.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.maskCPF() {
    mask("###.###.###-##")
}

fun EditText.maskCEP() {
    mask("#####-###")
}

fun EditText.maskCreditCard() {
    mask("#### #### #### ####")
}

fun EditText.maskCellPhone(hasCountryCode: Boolean) {
    if (hasCountryCode) mask("+## (##) #########")
    else mask("(##) #########")
}

fun EditText.mask(pattern: String) {
    this.addTextChangedListener(object : TextWatcher {
        var isUpdating: Boolean = false
        var previousString: String = ""

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val str = s.toString().replace("[./() \\-+]".toRegex(), "")
            var stringWithMask = ""

            if (count == 0) isUpdating = true

            if (isUpdating) {
                previousString = str
                isUpdating = false
                return
            }

            var i = 0
            run loop@{
                pattern.toCharArray().forEach {
                    if (it != '#' && str.length > previousString.length) {
                        stringWithMask += it
                        return@forEach
                    }

                    try {
                        stringWithMask += str[i]
                    } catch (e: Exception) {
                        return@loop
                    }
                    i++
                }
            }

            isUpdating = true
            this@mask.setText(stringWithMask)
            this@mask.setSelection(stringWithMask.length)
        }
    })
}