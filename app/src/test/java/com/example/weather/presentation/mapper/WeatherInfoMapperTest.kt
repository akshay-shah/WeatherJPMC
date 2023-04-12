package com.example.weather.presentation.mapper

import com.example.weather.mockWeatherDetailModel
import com.example.weather.mockWeatherInfoModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherInfoMapperTest {
    private lateinit var mapper: WeatherInfoMapper

    @Before
    fun setUp() {
        mapper = WeatherInfoMapper()
    }

    @Test
    fun `toModel for non null input should return WeatherInfoModel`() {
        val model = mapper.toModel(mockWeatherDetailModel)
        assertEquals(mockWeatherInfoModel.name, model.name)
        assertEquals(mockWeatherInfoModel.temperature, model.temperature)
        assertEquals(mockWeatherInfoModel.visibility, model.visibility)
        assertEquals(mockWeatherInfoModel.pressure, model.pressure)
        assertEquals(mockWeatherInfoModel.id, model.id)
        assertEquals(mockWeatherInfoModel.lat, model.lat, 0.0)
        assertEquals(mockWeatherInfoModel.long, model.long, 0.0)
        assertEquals(mockWeatherInfoModel.main, model.main)
        assertEquals(mockWeatherInfoModel.description, model.description)
        assertEquals(mockWeatherInfoModel.iconUrl, model.iconUrl)
        assertEquals(mockWeatherInfoModel.sunrise, model.sunrise)
        assertEquals(mockWeatherInfoModel.sunset, model.sunset)
    }
}