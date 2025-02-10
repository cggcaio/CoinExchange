package com.cggcaio.core.extension

import com.cggcaio.core.constants.DatePatternConstants.PRESENTATION_DATE_PATTERN
import com.cggcaio.core.constants.DatePatternConstants.REQUEST_COMPLETE_PATTERN
import java.text.SimpleDateFormat
import java.util.Locale

fun String.convertDateFormat(
    receivedPattern: String = REQUEST_COMPLETE_PATTERN,
    formatPattern: String = PRESENTATION_DATE_PATTERN,
): String {
    if (this.isBlank()) return this
    return try {
        val inputFormat = SimpleDateFormat(receivedPattern, Locale("pt", "BR"))
        val outputFormat = SimpleDateFormat(formatPattern, Locale("pt", "BR"))
        val date = inputFormat.parse(this)
        date?.let { return outputFormat.format(it) }
        this
    } catch (e: Exception) {
        this
    }
}
