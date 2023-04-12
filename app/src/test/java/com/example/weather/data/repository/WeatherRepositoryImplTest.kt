package com.example.weather.data.repository

import com.example.weather.data.mapper.CityNamesResponseMapper
import com.example.weather.data.mapper.WeatherDetailResponseMapper
import com.example.weather.data.source.DataSource
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.mockCityNameListEntity
import com.example.weather.mockGetWeatherByLatLongResponse
import com.example.weather.mockSearchCityByNameResponse
import com.example.weather.mockWeatherEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {
    @Mock
    private lateinit var weatherMapper: WeatherDetailResponseMapper

    @Mock
    private lateinit var cityNamesMapper: CityNamesResponseMapper

    @Mock
    private lateinit var remoteDataSource: DataSource.RemoteDataSource

    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = WeatherRepositoryImpl(weatherMapper, cityNamesMapper, remoteDataSource)
    }

    @Test
    fun `getWeatherByLatLong should return a valid response`() {
        runBlockingTest {
            whenever(weatherMapper.toModel(mockWeatherEntity)).thenReturn(
                mockGetWeatherByLatLongResponse
            )
            whenever(remoteDataSource.getWeatherByLatLong(lat = 18.01, long = 20.01)).thenReturn(
                mockWeatherEntity
            )
            val response = repository.getWeatherByLatLong(lat = 18.01, long = 20.01)
            verify(remoteDataSource).getWeatherByLatLong(lat = 18.01, long = 20.01)
            assertEquals(mockGetWeatherByLatLongResponse.weatherDetail, response.weatherDetail)
            assertEquals(mockGetWeatherByLatLongResponse.error, response.error)
        }
    }

    @Test
    fun `searchCityByName should return a valid response`() {
        runBlockingTest {
            whenever(cityNamesMapper.toModel(mockCityNameListEntity)).thenReturn(
                mockSearchCityByNameResponse
            )
            whenever(remoteDataSource.searchCityByName(name = "Pune")).thenReturn(
                mockCityNameListEntity
            )
            val response = repository.searchCityByName(city = "Pune")
            verify(remoteDataSource).searchCityByName(name = "Pune")
            assertEquals(mockSearchCityByNameResponse.cityNames, response.cityNames)
            assertEquals(mockSearchCityByNameResponse.error, response.error)
        }
    }
}