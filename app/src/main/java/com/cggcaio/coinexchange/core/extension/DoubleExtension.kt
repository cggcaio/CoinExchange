package com.cggcaio.coinexchange.core.extension

import java.text.NumberFormat
import java.util.Locale

fun Double.formatToDollar(): String {
    try {
        val dollarFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        dollarFormat.maximumFractionDigits = 2
        return dollarFormat.format(this)
    } catch (e: Exception) {
        return this.toString()
    }
}
