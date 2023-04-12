package com.example.weather.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherInfoModel(
    val name: String,
    val lat: Double,
    val long: Double,
    val id: Int,
    val main: String,
    val description: String,
    val iconUrl: String,
    val pressure: String,
    val humidity: String,
    val temperature: String,
    val country: String,
    val sunrise: String,
    val sunset: String,
    val visibility: String
)

data class CityCountryNameModel(
    val name: String,
    val lat: Double,
    val lon: Double
)