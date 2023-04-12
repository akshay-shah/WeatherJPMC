package com.example.weather.presentation.mapper

import com.example.weather.domain.model.CityNameModel
import com.example.weather.presentation.model.CityCountryNameModel

class CityCountryNameMapper : ModelMapper<List<CityNameModel>, List<CityCountryNameModel>> {
    override fun toModel(request: List<CityNameModel>?): List<CityCountryNameModel> =
        request!!.map { CityCountryNameModel(name = "${it.name} , ${it.country}", it.lat, it.lon) }
}