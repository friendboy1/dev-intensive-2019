package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.R


/**
 * @author Andrei Khromov on 2019-07-17
 */


fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let { v ->
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    val rootView: View = findViewById(R.id.content)
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val screenHeight = rootView.height
    val keypadHeight = screenHeight - visibleBounds.height()
    return keypadHeight > screenHeight * 0.15
}

fun Activity.isKeyboardClosed(): Boolean {
    return this.isKeyboardOpen().not()
}