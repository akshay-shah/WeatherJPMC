package com.example.weather.presentation.mapper

import com.example.weather.data.getIconUrl
import com.example.weather.data.kelvinToCelsius
import com.example.weather.data.unixTimestampToTimeString
import com.example.weather.domain.model.WeatherDetailModel
import com.example.weather.presentation.model.WeatherInfoModel

class WeatherInfoMapper : ModelMapper<WeatherDetailModel, WeatherInfoModel> {

    override fun toModel(request: WeatherDetailModel?): WeatherInfoModel =
        with(request!!) {
            WeatherInfoModel(
                name = "$name , $country",
                lat = lat,
                long = long,
                id = id,
                main = main,
                description = description,
                iconUrl = iconUrl.getIconUrl(),
                pressure = "$pressure mBar",
                humidity = "$humidity %",
                temperature = temperature.kelvinToCelsius().toString(),
                country = country,
                sunrise = sunrise.unixTimestampToTimeString(),
                sunset = sunset.unixTimestampToTimeString(),
                visibility = "$visibility Km"
            )
        }
}