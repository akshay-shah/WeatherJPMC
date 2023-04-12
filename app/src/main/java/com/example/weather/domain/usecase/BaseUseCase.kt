package com.example.weather.domain.usecase

abstract class BaseUseCase<P : BaseUseCase.Request, Q : BaseUseCase.Response> {
    interface Request
    interface Response

    abstract suspend fun executeUseCase(request: P): Q
}