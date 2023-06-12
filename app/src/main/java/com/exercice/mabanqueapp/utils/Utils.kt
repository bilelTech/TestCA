package com.exercice.mabanqueapp.utils

import android.text.format.DateFormat
import androidx.core.text.isDigitsOnly
import java.util.*

object Utils {

    fun getDate(time: String): String {
        if(time.isEmpty() || !time.isDigitsOnly()) return ""
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time.toLong() * 1000
        return DateFormat.format("dd/MM/yyyy", cal).toString()
    }
}