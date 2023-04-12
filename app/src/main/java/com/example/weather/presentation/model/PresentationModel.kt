package com.example.weather.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherInfoModel(
    val name: String,
    val lat: Double,
    val long: Double,
    val id: Int,
    val main: String,
    val description: String,
    val iconUrl: String,
    val pressure: String,
    val humidity: String,
    val temperature: String,
    val country: String,
    val sunrise: String,
    val sunset: String,
    val visibility: String
)

data class CityCountryNameModel(
    val name: String,
    val lat: Double,
    val lon: Double
)

@Parcelize
data class CharacterInfoModel(
    val birth_year: String?,
    val filmsIdList: List<String>?,
    val height: String?,
    val homeworldId: String?,
    val name: String?,
    val specieIdList: List<String>?
) : Parcelable

@Parcelize
data class CharacterNamesModel(val characterNamesList: List<String>?) : Parcelable

@Parcelize
data class CharacterDetailsModel(
    val birth_year: String = "",
    val filmDetails: String = "",
    val height: String = "",
    val name: String = "",
    val specieName: String = "",
    val language: String = "",
    val homeworld: String = "",
    val planetPopulation: String = "",
) : Parcelable