package com.example.weather.data.source.remote

import com.example.weather.data.source.remote.service.ApiService
import com.example.weather.mockCityNameListEntity
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
class RemoteDataSourceImplTest {
    @Mock
    private lateinit var service: ApiService

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(service)
    }

    @Test
    fun `getWeatherByLatLong should return response from ApiService`() {
        runBlockingTest {
            whenever(service.getWeatherByLatLong(lat = 19.01, long = 20.0)).thenReturn(
                mockWeatherEntity
            )
            val response = remoteDataSourceImpl.getWeatherByLatLong(lat = 19.01, long = 20.0)
            verify(service).getWeatherByLatLong(lat = 19.01, long = 20.0)
            assertEquals(mockWeatherEntity, response)
        }
    }

    @Test
    fun `searchCityByName should return response from ApiService`() {
        runBlockingTest {
            whenever(service.searchCityByName(searchQuery = "Pune")).thenReturn(
                mockCityNameListEntity
            )
            val response = remoteDataSourceImpl.searchCityByName(name = "Pune")
            verify(service).searchCityByName(searchQuery = "Pune")
            assertEquals(mockCityNameListEntity, response)
        }
    }

    @Test
    fun `getWeatherByLatLong for invalid data should return null`() {
        runBlockingTest {
            whenever(service.getWeatherByLatLong(lat = 19.01, long = 20.0)).thenReturn(null)
            val response = remoteDataSourceImpl.getWeatherByLatLong(lat = 19.01, long = 20.0)
            verify(service).getWeatherByLatLong(lat = 19.01, long = 20.0)
            assertEquals(null, response)
        }
    }

    @Test
    fun `searchCityByName for invalid data should return null`() {
        runBlockingTest {
            whenever(service.searchCityByName(searchQuery = "Pune")).thenReturn(null)
            val response = remoteDataSourceImpl.searchCityByName(name = "Pune")
            verify(service).searchCityByName(searchQuery = "Pune")
            assertEquals(null, response)
        }
    }

}