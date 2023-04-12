package com.example.weather.data.source.remote

import android.util.Log
import com.example.weather.data.model.CityNameEntity
import com.example.weather.data.model.WeatherEntity
import com.example.weather.data.source.DataSource
import com.example.weather.data.source.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val remoteService: ApiService) :
    DataSource.RemoteDataSource {

    override suspend fun getWeatherByLatLong(lat: Double, long: Double): WeatherEntity? =
        try {
            remoteService.getWeatherByLatLong(lat, long)
        } catch (error: Exception) {
            Log.i("Error", error.message.toString())
            null
        }

    override suspend fun searchCityByName(name: String): List<CityNameEntity>? =
        try {
            remoteService.searchCityByName(name)
        } catch (error: Exception) {
            null
        }

}