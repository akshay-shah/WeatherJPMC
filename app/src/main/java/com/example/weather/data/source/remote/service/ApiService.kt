package com.example.weather.data.source.remote.service

import com.example.weather.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

const val APP_ID = "d28cec68d2273eb04d8cbf544b56af05"
const val SEARCH_LIMIT = 5

interface ApiService {

    @GET("/data/2.5/weather")
    suspend fun getWeatherByLatLong(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appid") appId: String = APP_ID
    ): WeatherEntity

    @GET("/geo/1.0/direct")
    suspend fun searchCityByName(
        @Query("q") searchQuery: String,
        @Query("limit") limit: Int = SEARCH_LIMIT,
        @Query("appid") appId: String = APP_ID
    ): List<CityNameEntity>
}