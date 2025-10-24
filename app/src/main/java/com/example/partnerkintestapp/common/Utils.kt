package com.example.partnerkintestapp.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun parseDate(dateString: String): Calendar =
    Calendar.getInstance().apply {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        time = format.parse(dateString)
    }