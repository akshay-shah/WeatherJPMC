package com.example.weather.data.mapper

import com.example.weather.data.model.CityNameEntity
import com.example.weather.domain.model.CityNameModel
import com.example.weather.domain.usecase.SearchCityByNameUseCase

class CityNamesResponseMapper :
    ResponseMapper<List<CityNameEntity>, SearchCityByNameUseCase.SearchCityByNameResponse> {
    override fun toModel(response: List<CityNameEntity>?): SearchCityByNameUseCase.SearchCityByNameResponse {
        return when (response) {
            null -> SearchCityByNameUseCase.SearchCityByNameResponse(
                cityNames = listOf(),
                error = true
            )
            else -> SearchCityByNameUseCase.SearchCityByNameResponse(cityNames = response.map {
                CityNameModel(name = it.name, lat = it.lat, lon = it.lon, country = it.country)
            })
        }
    }
}