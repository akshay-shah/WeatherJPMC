package com.example.weather.presentation.mapper

interface ModelMapper<P, Q> {
    fun toModel(request: P?): Q
}