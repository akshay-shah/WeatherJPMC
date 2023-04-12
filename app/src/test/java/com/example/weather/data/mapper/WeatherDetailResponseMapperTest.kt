package com.example.weather.data.mapper

import com.example.weather.mockGetWeatherByLatLongResponse
import com.example.weather.mockWeatherEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class WeatherDetailResponseMapperTest {
    private lateinit var mapper: WeatherDetailResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = WeatherDetailResponseMapper()
    }

    @Test
    fun `toModel for valid data should return use case response`() {
        val response = mapper.toModel(mockWeatherEntity)
        Assert.assertEquals(mockGetWeatherByLatLongResponse.weatherDetail, response.weatherDetail)
        Assert.assertEquals(mockGetWeatherByLatLongResponse.error, response.error)
        Assert.assertFalse(response.error)
    }

    @Test
    fun `toModel for invalid data should return use case response error`() {
        val response = mapper.toModel(null)
        Assert.assertTrue(response.error)
    }
}