package com.example.weather.presentation.mapper

import com.example.weather.mockCityCountryNameModel
import com.example.weather.mockCityNameListModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CityCountryNameMapperTest {
    private lateinit var mapper: CityCountryNameMapper

    @Before
    fun setUp() {
        mapper = CityCountryNameMapper()
    }

    @Test
    fun `toModel for non null input should return CityContryNameModel`() {
        val model = mapper.toModel(mockCityNameListModel)
        Assert.assertEquals(mockCityCountryNameModel.size, model.size)
        for (i in model.indices) {
            Assert.assertEquals(mockCityCountryNameModel.get(i).name, model.get(i).name)
            Assert.assertEquals(mockCityCountryNameModel.get(i).lat, model.get(i).lat, 0.0)
            Assert.assertEquals(mockCityCountryNameModel.get(i).lon, model.get(i).lon, 0.0)
        }
    }
}