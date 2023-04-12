package com.example.weather.presentation.di
//
import com.example.weather.data.mapper.*
import com.example.weather.data.repository.*
import com.example.weather.data.source.DataSource
import com.example.weather.data.source.remote.RemoteDataSourceImpl
import com.example.weather.data.source.remote.service.ApiService
import com.example.weather.domain.repository.*
import com.example.weather.domain.usecase.*
import com.example.weather.presentation.mapper.CityCountryNameMapper
import com.example.weather.presentation.mapper.WeatherInfoMapper
import com.example.weather.presentation.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("https://api.openweathermap.org/").client(
            client
        ).addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): DataSource.RemoteDataSource =
        RemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideWeatherDetailResponseMapper() = WeatherDetailResponseMapper()

    @Singleton
    @Provides
    fun provideCityNamesResponseMapper() = CityNamesResponseMapper()

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherResponseMapper: WeatherDetailResponseMapper,
        cityNamesResponseMapper: CityNamesResponseMapper,
        remoteDataSource: DataSource.RemoteDataSource
    ): WeatherRepository =
        WeatherRepositoryImpl(weatherResponseMapper, cityNamesResponseMapper, remoteDataSource)


    @Singleton
    @Provides
    fun provideGetWeatherByLatLongUseCase(repository: WeatherRepository): GetWeatherByLatLongUseCase =
        GetWeatherByLatLongUseCase(repository)

    @Singleton
    @Provides
    fun provideSearchCityByNameUseCase(repository: WeatherRepository): SearchCityByNameUseCase =
        SearchCityByNameUseCase(repository)

    @Singleton
    @Provides
    fun provideCityCountryNameMapper() = CityCountryNameMapper()

    @Singleton
    @Provides
    fun provideWeatherInfoMapper() = WeatherInfoMapper()

    @Singleton
    @Provides
    fun provideHomeViewModel(
        getWeatherByLatLongUseCase: GetWeatherByLatLongUseCase,
        searchCityByNameUseCase: SearchCityByNameUseCase,
        cityCountryNameMapper: CityCountryNameMapper,
        weatherInfoMapper: WeatherInfoMapper
    ): HomeViewModel = HomeViewModel(
        null,
        searchCityByNameUseCase,
        getWeatherByLatLongUseCase,
        cityCountryNameMapper,
        weatherInfoMapper
    )

}