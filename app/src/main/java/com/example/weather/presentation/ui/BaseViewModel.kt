package com.example.weather.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

open class BaseViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>(false)
    val showError = MutableLiveData<Boolean>(false)
    val showSuccess = MutableLiveData<Boolean>(false)
}

fun ViewModel.getViewModelScope(coroutineScope: CoroutineScope?) =
    coroutineScope ?: this.viewModelScope