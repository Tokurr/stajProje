package com.tayyib.myapplication.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun parseAndFormatDate(dateString: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val adjustedDateString = if (dateString.contains(".")) {
                dateString.substringBeforeLast(".") + "." + dateString.substringAfterLast(".")
                    .padEnd(3, '0')
            } else {
                dateString
            }
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
            val dateTime = LocalDateTime.parse(adjustedDateString, formatter)
            val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
            dateTime.format(outputFormatter)
        } catch (e: DateTimeParseException) {
            dateString
        }
    } else {
        try {
            val adjustedDateString = if (dateString.contains(".")) {
                dateString.substringBeforeLast(".") + "." + dateString.substringAfterLast(".")
                    .padEnd(3, '0')
            } else {
                dateString
            }
            val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
            val outputFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            val date = inputFormatter.parse(adjustedDateString)
            outputFormatter.format(date ?: "")
        } catch (e: Exception) {
            dateString
        }
    }
}
