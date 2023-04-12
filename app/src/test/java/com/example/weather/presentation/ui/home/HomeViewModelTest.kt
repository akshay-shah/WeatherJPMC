package com.example.weather.presentation.ui.home

import com.example.weather.*
import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase
import com.example.weather.domain.usecase.SearchCityByNameUseCase
import com.example.weather.presentation.mapper.CityCountryNameMapper
import com.example.weather.presentation.mapper.WeatherInfoMapper
import com.example.weather.presentation.ui.BaseViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var getWeatherByLatLongUseCase: GetWeatherByLatLongUseCase

    @Mock
    private lateinit var searchCityByNameUseCase: SearchCityByNameUseCase

    @Mock
    private lateinit var weatherInfoMapper: WeatherInfoMapper

    @Mock
    private lateinit var cityCountryNameMapper: CityCountryNameMapper


    private lateinit var viewModel: HomeViewModel


    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            HomeViewModel(
                testScope,
                searchCityByNameUseCase,
                getWeatherByLatLongUseCase,
                cityCountryNameMapper,
                weatherInfoMapper
            )
    }

    @Test
    fun `getWeather should call getWeatherByLatLongUseCase and post success data`() {
        runBlockingTest {
            whenever(getWeatherByLatLongUseCase.executeUseCase(any())).thenReturn(
                mockGetWeatherByLatLongResponse
            )
            whenever(weatherInfoMapper.toModel(any())).thenReturn(mockWeatherInfoModel)
            viewModel.getWeather(18.0, 19.0)
            assertEquals(viewModel.weatherInfoModel.value, mockWeatherInfoModel)
        }
    }

    @Test
    fun `getWeather should call getWeatherByLatLongUseCase and post error data`() {
        runBlockingTest {
            whenever(getWeatherByLatLongUseCase.executeUseCase(any())).thenReturn(
                mockErrorGetWeatherByLatLongResponse
            )
            viewModel.getWeather(18.0, 19.0)
            assertEquals(viewModel.showError.value, true)
        }
    }

    @Test
    fun `searchCityByName should call searchCityByNameUseCase and post success data`() {
        runBlockingTest {
            whenever(searchCityByNameUseCase.executeUseCase(any())).thenReturn(
                mockSearchCityByNameResponse
            )
            whenever(cityCountryNameMapper.toModel(any())).thenReturn(mockCityCountryNameModel)
            viewModel.searchCity("xyz")
            assertEquals(viewModel.cityCountryNameModel.value, mockCityCountryNameModel)
        }
    }

    @Test
    fun `searchCityByName should call searchCityByNameUseCase and post error data`() {
        runBlockingTest {
            whenever(searchCityByNameUseCase.executeUseCase(any())).thenReturn(
                mockErrorSearchCityByNameResponse
            )
            viewModel.searchCity("xyz")
            assertEquals(viewModel.showError.value, true)
        }
    }
}