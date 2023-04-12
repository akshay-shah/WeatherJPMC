package com.example.weather.data.mapper

import com.example.weather.mockCityNameListEntity
import com.example.weather.mockSearchCityByNameResponse
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class CityNamesResponseMapperTest {
    private lateinit var mapper: CityNamesResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = CityNamesResponseMapper()
    }

    @Test
    fun `toModel for valid data should return use case response`() {
        val response = mapper.toModel(mockCityNameListEntity)
        assertEquals(mockSearchCityByNameResponse.cityNames, response.cityNames)
        assertEquals(mockSearchCityByNameResponse.error, response.error)
        Assert.assertFalse(response.error)
    }

    @Test
    fun `toModel for invalid data should return use case response error`() {
        val response = mapper.toModel(null)
        Assert.assertTrue(response.error)
    }
}