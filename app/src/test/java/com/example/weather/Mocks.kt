package com.example.weather

import com.example.weather.data.model.*
import com.example.weather.domain.model.CityNameModel
import com.example.weather.domain.model.WeatherDetailModel
import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase
import com.example.weather.domain.usecase.SearchCityByNameUseCase
import com.example.weather.presentation.model.CityCountryNameModel
import com.example.weather.presentation.model.WeatherInfoModel

val mockCityNameListEntity = listOf(
    CityNameEntity(name = "Pune", lat = 18.09, lon = 19.01, country = "In"),
    CityNameEntity(name = "Mumbai", lat = 18.09, lon = 19.01, country = "In"),
    CityNameEntity(name = "Delhi", lat = 18.09, lon = 19.01, country = "In"),
    CityNameEntity(name = "Bangalore", lat = 18.09, lon = 19.01, country = "In")
)

val mockCityNameListModel = listOf(
    CityNameModel(name = "Pune", lat = 18.09, lon = 19.01, country = "In"),
    CityNameModel(name = "Mumbai", lat = 18.09, lon = 19.01, country = "In"),
    CityNameModel(name = "Delhi", lat = 18.09, lon = 19.01, country = "In"),
    CityNameModel(name = "Bangalore", lat = 18.09, lon = 19.01, country = "In"),
)

val mockSearchCityByNameResponse = SearchCityByNameUseCase.SearchCityByNameResponse(
    mockCityNameListModel, false
)

val mockErrorSearchCityByNameResponse = SearchCityByNameUseCase.SearchCityByNameResponse(
    listOf(), true
)

val mockWeatherEntity = WeatherEntity(
    coord = CoordEntity(lat = 18.09, lon = 19.01),
    name = "Pune",
    weather = listOf(
        WeatherDetailEntity(
            id = 213,
            main = "rain",
            description = "rainy",
            icon = "10d"
        )
    ),
    base = "stations",
    main = MainEntity(
        temp = 300.76,
        feels_like = 300.61,
        temp_min = 300.76,
        temp_max = 300.76,
        pressure = 1014,
        humidity = 42,
        sea_level = 1014,
        grnd_level = 953
    ),
    visibility = 10000,
    wind = WindEntity(
        speed = 1.07,
        deg = 320,
        gust = 1.35
    ),
    clouds = CloudsEntity(
        all = 0,
    ),
    dt = 1681264456,
    sys = SysEntity(
        country = "IN",
        sunrise = 1681260618,
        sunset = 1681305620
    ),
    timezone = 19800,
    id = 1259229,
    cod = 200
)

val mockWeatherDetailModel = WeatherDetailModel(
    name = "Pune",
    lat = 18.09,
    long = 19.01,
    id = 213,
    main = "rain",
    description = "rainy",
    iconUrl = "10d",
    pressure = 1014,
    humidity = 42,
    temperature = 300.76,
    country = "IN",
    sunrise = 1681260618,
    sunset = 1681305620,
    visibility = 10
)

val mockGetWeatherByLatLongResponse = GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse(
    weatherDetail = mockWeatherDetailModel, error = false
)

val mockErrorGetWeatherByLatLongResponse = GetWeatherByLatLongUseCase.GetWeatherByLatLongResponse(
    weatherDetail = null, error = true
)


val mockGetWeatherByLatLongRequest =
    GetWeatherByLatLongUseCase.GetWeatherByLatLongRequest(lat = 19.0, long = 20.0)

val mockSearchCityByNameRequest =
    SearchCityByNameUseCase.SearchCityByNameRequest(cityName = "Pune")

val mockWeatherInfoModel = WeatherInfoModel(
    name = "Pune , IN",
    lat = 18.09,
    long = 19.01,
    id = 213,
    main = "rain",
    description = "rainy",
    iconUrl = "https://openweathermap.org/img/wn/10d@4x.png",
    pressure = "1014 hPa",
    humidity = "42%",
    temperature = "27",
    country = "IN",
    sunrise = "06:20 AM",
    sunset = "06:50 PM",
    visibility = "10 Km"
)

val mockCityCountryNameModel = listOf(
    CityCountryNameModel(name = "Pune , In", lat = 18.09, lon = 19.01),
    CityCountryNameModel(name = "Mumbai , In", lat = 18.09, lon = 19.01),
    CityCountryNameModel(name = "Delhi , In", lat = 18.09, lon = 19.01),
    CityCountryNameModel(name = "Bangalore , In", lat = 18.09, lon = 19.01),
)

