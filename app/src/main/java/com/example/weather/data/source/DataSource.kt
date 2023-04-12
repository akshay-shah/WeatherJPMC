package com.example.weather.data.source

import com.example.weather.data.model.CityNameEntity
import com.example.weather.data.model.WeatherEntity

interface DataSource {
    interface RemoteDataSource {
        suspend fun getWeatherByLatLong(lat: Double, long: Double): WeatherEntity?
        suspend fun searchCityByName(name: String): List<CityNameEntity>?
    }
}