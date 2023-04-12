package com.example.weather.domain.usecase

import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.mockSearchCityByNameRequest
import com.example.weather.mockSearchCityByNameResponse
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
class SearchCityByNameUseCaseTest {
    @Mock
    private lateinit var repository: WeatherRepository

    private lateinit var useCase: SearchCityByNameUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SearchCityByNameUseCase(repository)
    }

    @Test
    fun `executeUseCase should call repository`() {
        runBlockingTest {
            whenever(repository.searchCityByName(city = "Pune")).thenReturn(
                mockSearchCityByNameResponse
            )
            val response =
                useCase.executeUseCase(mockSearchCityByNameRequest)
            verify(repository).searchCityByName(city = "Pune")
            Assert.assertEquals(mockSearchCityByNameResponse, response)
        }
    }
}