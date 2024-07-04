package com.comppot.mindsnack.ui.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    private const val DATE_FORMAT = "MMM d"

    fun formatDate(milliseconds: Long): String {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        return formatter.format(calendar.time)
    }
}