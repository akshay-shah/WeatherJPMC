package com.example.weather.data.mapper

import com.example.weather.data.model.WeatherEntity
import com.example.weather.domain.model.WeatherDetailModel
import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase

class WeatherDetailResponseMapper :
    ResponseMapper<WeatherEntity, GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse> {
    override fun toModel(response: WeatherEntity?): GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse {
        return when (response) {
            null -> GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse(
                weatherDetail = null,
                error = true
            )
            else -> GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse(weatherDetail = response.let {
                WeatherDetailModel(
                    name = it.name,
                    lat = it.coord.lat,
                    long = it.coord.lon,
                    id = it.weather.first().id,
                    main = it.weather.first().main,
                    description = it.weather.first().description,
                    iconUrl = it.weather.first().icon,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity,
                    visibility = it.visibility / 1000,
                    country = it.sys.country,
                    sunrise = it.sys.sunrise,
                    sunset = it.sys.sunset,
                    temperature = it.main.temp
                )
            }, error = false)
        }
    }
}