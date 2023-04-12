package com.example.weather.data.mapper

import com.example.weather.domain.usecase.BaseUseCase

interface ResponseMapper<P, Q : BaseUseCase.Response> {
    fun toModel(response: P?): Q
}