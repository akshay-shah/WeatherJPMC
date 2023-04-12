package com.example.weather.domain.model

data class WeatherDetailModel(
    val name: String,
    val lat: Double,
    val long: Double,
    val id: Int,
    val main: String,
    val description: String,
    val iconUrl: String,
    val pressure: Int,
    val humidity: Int,
    val temperature: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val visibility: Int
)

data class CityNameModel(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String
)