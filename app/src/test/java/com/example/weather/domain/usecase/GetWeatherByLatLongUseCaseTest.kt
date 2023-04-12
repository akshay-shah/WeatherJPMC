package com.example.weather.domain.usecase

import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.mockGetWeatherByLatLongRequest
import com.example.weather.mockGetWeatherByLatLongResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetWeatherByLatLongUseCaseTest {
    @Mock
    private lateinit var repository: WeatherRepository

    private lateinit var useCase: GetWeatherByLatLongUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetWeatherByLatLongUseCase(repository)
    }

    @Test
    fun `executeUseCase should call repository`() {
        runBlockingTest {
            whenever(repository.getWeatherByLatLong(lat = 19.0, long = 20.0)).thenReturn(
                mockGetWeatherByLatLongResponse
            )
            val response =
                useCase.executeUseCase(mockGetWeatherByLatLongRequest)
            verify(repository).getWeatherByLatLong(lat = 19.0, long = 20.0)
            Assert.assertEquals(mockGetWeatherByLatLongResponse, response)
        }
    }
}