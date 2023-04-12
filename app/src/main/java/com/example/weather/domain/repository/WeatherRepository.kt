package com.example.weather.domain.repository

import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase
import com.example.weather.domain.usecase.SearchCityByNameUseCase

interface WeatherRepository {
    suspend fun getWeatherByLatLong(
        lat: Double,
        long: Double
    ): GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse

    suspend fun searchCityByName(city: String): SearchCityByNameUseCase.SearchCityByNameResponse

}