package com.example.weather.domain.usecase

import com.example.weather.domain.model.WeatherDetailModel
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject


class GetWeatherByLatLongUseCase @Inject constructor(private val repository: WeatherRepository) :
    BaseUseCase<GetWeatherByLatLongUseCase.GetWeatherByLatLongRequest, GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse>() {
    class GetWeatherByLatLongRequest(val lat: Double, val long: Double) : BaseUseCase.Request
    class GetWeatherByLatLongResponse(
        val weatherDetail: WeatherDetailModel?,
        val error: Boolean = false
    ) : BaseUseCase.Response

    override suspend fun executeUseCase(request: GetWeatherByLatLongRequest): GetWeatherByLatLongResponse =
        repository.getWeatherByLatLong(request.lat, request.long)
}