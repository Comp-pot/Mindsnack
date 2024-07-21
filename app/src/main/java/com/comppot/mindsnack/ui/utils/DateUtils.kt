package com.comppot.mindsnack.ui.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object DateUtils {
    private const val DATE_FORMAT = "MMM d"

    fun formatDate(timestamp: Long): String {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000
        return formatter.format(calendar.time)
    }

    fun getTimeDifference(timestamp: Long): String {
        val currentTime = System.currentTimeMillis() / 1000
        val diffInSeconds = currentTime - timestamp

        val duration = diffInSeconds.toDuration(DurationUnit.SECONDS)

        return when {
            duration.inWholeSeconds < 60 -> "${duration.inWholeSeconds}s"
            duration.inWholeMinutes < 60 -> "${duration.inWholeMinutes}m"
            duration.inWholeHours < 24 -> "${duration.inWholeHours}h"
            duration.inWholeDays < 7 -> "${duration.inWholeDays}d"
            else -> "${duration.inWholeDays / 7}w"
        }
    }
}