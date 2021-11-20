package com.haditorfi.minaz.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import java.text.NumberFormat

const val DATA_KEY = "data"
const val ID_KEY = "id"

fun formatPrice(price: Int): String {
    val currencyLabel = "تومان"
    val formatter: NumberFormat = DecimalFormat("#,###")
    val formattedNumber = formatter.format(price)
    return "$formattedNumber $currencyLabel"
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.snackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}

fun View.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}

fun String.isPhone(): Boolean {
    val p = "^0([9])\\d{9}\$".toRegex()
    return matches(p)
}