package com.xtremsystems.patientscheduler.helpers

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DatesFormat {
    @SuppressLint("ConstantLocale")
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun getMonthName(dateString: String): String {
        val cal = Calendar.getInstance()
        val date = df.parse(dateString)
        cal.time = date
        return SimpleDateFormat("MMM", Locale.getDefault()).format(df.parse(dateString))
    }

    fun getMonthAndYear(dateString: String):String{
        val cal = Calendar.getInstance()
        val date = df.parse(dateString)
        cal.time = date
        return SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(df.parse(dateString))
    }

    fun getYear(dateString: String): String {

        val date = df.parse(dateString)
        val cal = Calendar.getInstance()
        cal.time = date
        return SimpleDateFormat("yyyy", Locale.getDefault()).format(date)
    }

    fun getFullDate(dateString: String): String {
        val date = df.parse(dateString)
        val cal = Calendar.getInstance()
        cal.time = date
        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }

    fun getFullDateAndTime(dateString: String): String {
        val date = df.parse(dateString)
        val cal = Calendar.getInstance()
        cal.time = date
        return SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault()).format(date)
    }

    fun getDayAndMonth(dateString: String): String {
        val date = df.parse(dateString)
        val cal = Calendar.getInstance()
        cal.time = date
        return SimpleDateFormat("dd MMM", Locale.getDefault()).format(df)
    }

    fun getDay(dateString: String): Int {
        val date = df.parse(dateString)
        val cal = Calendar.getInstance()
        cal.time = date
        return SimpleDateFormat("dd", Locale.getDefault()).format(date).toInt()
    }

    fun getTodayDay(): String {
        return SimpleDateFormat("dd", Locale.getDefault()).format(Calendar.getInstance().time)
    }

    fun getTodayMonth(): String {
        return SimpleDateFormat("MMM", Locale.getDefault()).format(Calendar.getInstance().time)

    }

    fun getTodayYear(): String {
        return SimpleDateFormat("yyyy", Locale.getDefault()).format(Calendar.getInstance().time)

    }

}