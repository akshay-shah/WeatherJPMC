package com.example.weather.data

import java.text.SimpleDateFormat
import java.util.*

const val BASE_ICON_URL = "https://openweathermap.org/img/wn/"
const val IMAGE_FORMAT_EXT = "@4x.png"


fun Int.unixTimestampToTimeString(): String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}


fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).toInt()
}

fun String.getIconUrl(): String {
    return BASE_ICON_URL.plus(this).plus(IMAGE_FORMAT_EXT)
}