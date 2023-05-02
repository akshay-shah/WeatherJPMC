package com.example.weather.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.weather.domain.usecase.GetWeatherByLatLongUseCase
import com.example.weather.domain.usecase.SearchCityByNameUseCase
import com.example.weather.presentation.mapper.CityCountryNameMapper
import com.example.weather.presentation.mapper.WeatherInfoMapper
import com.example.weather.presentation.model.CityCountryNameModel
import com.example.weather.presentation.model.WeatherInfoModel
import com.example.weather.presentation.ui.BaseViewModel
import com.example.weather.presentation.ui.getViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coroutineScopeProvider: CoroutineScope? = null,
    private val searchCityByNameUseCase: SearchCityByNameUseCase,
    private val getWeatherByLatLongUseCase: GetWeatherByLatLongUseCase,
    private val cityCountryNameMapper: CityCountryNameMapper,
    private val weatherInfoMapper: WeatherInfoMapper
) : BaseViewModel() {

    private val coroutineScope = getViewModelScope(coroutineScopeProvider)
    val weatherInfoModel = MutableLiveData<WeatherInfoModel>()
    val cityCountryNameModel = MutableLiveData<List<CityCountryNameModel>>()

    val onListItemClickListener: (position: Int) -> Unit = { clickedPosition ->
        val cityCountryName = cityCountryNameModel.value?.get(clickedPosition)!!
        getWeather(cityCountryName.lat, cityCountryName.lon)
    }

    fun getWeather(lat: Double, lon: Double) {
        coroutineScope.launch {
            showLoading.postValue(true)
            showError.postValue(false)
            val response = getWeatherByLatLongUseCase.executeUseCase(
                GetWeatherByLatLongUseCase.GetWeatherByLatLongRequest(
                    lat,
                    lon
                )
            )
            when (response.error) {
                true -> {
                    showLoading.postValue(false)
                    showError.postValue(true)
                }
                false -> {
                    showLoading.postValue(false)
                    showSuccess.postValue(true)
                    val mappedData = weatherInfoMapper.toModel(response.weatherDetail)
                    weatherInfoModel.postValue(mappedData)
                }
            }
        }
    }

    fun searchCity(query: String?) {
        coroutineScope.launch {
            showError.postValue(false)
            val response = searchCityByNameUseCase.executeUseCase(
                SearchCityByNameUseCase.SearchCityByNameRequest(
                    query ?: ""
                )
            )
            when (response.error) {
                true -> {
                    showError.postValue(true)
                }
                false -> {
                    when (response.cityNames?.size?.compareTo(0)) {
                        0 -> {
                        }
                        1 -> {
                            val mappedData =
                                cityCountryNameMapper.toModel(response.cityNames?.toList())
                            cityCountryNameModel.postValue(mappedData)
                        }
                    }

                }
            }
        }
    }

}