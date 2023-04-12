package com.example.weather.presentation.ui.home

import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase
import com.example.weather.domain.usecase.SearchCityByNameUseCase
import com.example.weather.mockCityCountryNameModel
import com.example.weather.mockSearchCityByNameResponse
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
import org.mockito.kotlin.verify
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
    fun `onqueryTextListener should call usecase and post value to cityCountryModel`() {
        testScope.runBlockingTest {
            whenever(
                searchCityByNameUseCase.executeUseCase(
                    any()
                )
            ).thenReturn(
                mockSearchCityByNameResponse
            )
            whenever(cityCountryNameMapper.toModel(any())).thenReturn(mockCityCountryNameModel)
            viewModel.queryTextListener.afterTextChanged(any())
            assertEquals(mockCityCountryNameModel, viewModel.cityCountryNameModel.value)
        }
    }
//
//    @Test
//    fun `onQueryTextSubmit should call search character use case with valid response`() {
//        testScope.runBlockingTest {
//            whenever(
//                searchCharacterUseCase.executeUseCase(
//                    any()
//                )
//            ).thenReturn(
//                mockSearchCharacterResponse
//            )
//            whenever(characterNameMapper.toModel(any())).thenReturn(mockCharacterNamesModel)
//            val argumentCaptor = argumentCaptor<SearchCharacterUseCase.SearchCharacterRequest>()
//            viewModel.queryTextListener.onQueryTextSubmit("xyz")
//            verify(searchCharacterUseCase).executeUseCase(
//                argumentCaptor.capture()
//            )
//            assertEquals("xyz", argumentCaptor.firstValue.query)
//            assertEquals(viewModel.showSuccess.value, true)
//            assertEquals(viewModel.showError.value, false)
//            assertEquals(viewModel.showLoading.value, false)
//            assertEquals(mockCharacterNamesModel, viewModel.characterNameList.value)
//        }
//    }
//
//    @Test
//    fun `onQueryTextSubmit should call search character use case with error`() {
//        testScope.runBlockingTest {
//            whenever(
//                searchCharacterUseCase.executeUseCase(
//                    any()
//                )
//            ).thenReturn(
//                SearchCharacterUseCase.SearchCharacterResponse(characterModel = null, error = true)
//            )
//            whenever(characterNameMapper.toModel(any())).thenReturn(mockCharacterNamesModel)
//            val argumentCaptor = argumentCaptor<SearchCharacterUseCase.SearchCharacterRequest>()
//            viewModel.queryTextListener.onQueryTextSubmit("xyz")
//            verify(searchCharacterUseCase).executeUseCase(
//                argumentCaptor.capture()
//            )
//            assertEquals("xyz", argumentCaptor.firstValue.query)
//            assertEquals(viewModel.showSuccess.value, false)
//            assertEquals(viewModel.showError.value, true)
//            assertEquals(viewModel.showLoading.value, false)
//        }
//    }
//
//    @Test
//    fun `onQueryTextSubmit should call search character use case with empty list response`() {
//        testScope.runBlockingTest {
//            whenever(
//                searchCharacterUseCase.executeUseCase(
//                    any()
//                )
//            ).thenReturn(
//                SearchCharacterUseCase.SearchCharacterResponse(characterModel = emptyList(), error = false)
//            )
//            whenever(characterNameMapper.toModel(any())).thenReturn(mockCharacterNamesModel)
//            val argumentCaptor = argumentCaptor<SearchCharacterUseCase.SearchCharacterRequest>()
//            viewModel.queryTextListener.onQueryTextSubmit("xyz")
//            verify(searchCharacterUseCase).executeUseCase(
//                argumentCaptor.capture()
//            )
//            assertEquals("xyz", argumentCaptor.firstValue.query)
//            assertEquals(viewModel.showSuccess.value, false)
//            assertEquals(viewModel.showError.value, true)
//            assertEquals(viewModel.showLoading.value, false)
//        }
//    }
}