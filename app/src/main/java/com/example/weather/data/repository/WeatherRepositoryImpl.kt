package com.example.weather.data.repository

import com.example.weather.data.mapper.CityNamesResponseMapper
import com.example.weather.data.mapper.WeatherDetailResponseMapper
import com.example.weather.data.source.DataSource
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase
import com.example.weather.domain.usecase.SearchCityByNameUseCase
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherResponseMapper: WeatherDetailResponseMapper,
    private val cityNameResponseMapper: CityNamesResponseMapper,
    private val remoteDataSource: DataSource.RemoteDataSource
) : WeatherRepository {
    override suspend fun getWeatherByLatLong(
        lat: Double,
        long: Double
    ): GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse =
        weatherResponseMapper.toModel(remoteDataSource.getWeatherByLatLong(lat, long))

    override suspend fun searchCityByName(city: String): SearchCityByNameUseCase.SearchCityByNameResponse =
        cityNameResponseMapper.toModel(remoteDataSource.searchCityByName(city))
}