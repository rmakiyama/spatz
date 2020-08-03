package com.rmakiyama.spatz.core.extension

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun View.showKeyboard() {
    val manager = context.getSystemService<InputMethodManager>()
    manager?.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}
