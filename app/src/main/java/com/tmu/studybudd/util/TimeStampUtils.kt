package com.tmu.studybudd.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeStampUtils {
    private const val pattern = "yyyy MMM dd HH:mm"

    fun formatTimestampToDateString(timestampInMillis: Long): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val date = Date(timestampInMillis)
        return dateFormat.format(date)
    }
}
