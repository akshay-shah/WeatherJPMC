package com.example.weather.domain.usecase

import com.example.weather.domain.model.CityNameModel
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class SearchCityByNameUseCase @Inject constructor(private val repository: WeatherRepository) :
    BaseUseCase<SearchCityByNameUseCase.SearchCityByNameRequest, SearchCityByNameUseCase.SearchCityByNameResponse>() {
    class SearchCityByNameRequest(val cityName: String) : BaseUseCase.Request
    class SearchCityByNameResponse(
        val cityNames: List<CityNameModel>?,
        val error: Boolean = false
    ) : BaseUseCase.Response

    override suspend fun executeUseCase(request: SearchCityByNameRequest): SearchCityByNameResponse =
        repository.searchCityByName(request.cityName)
}