package com.sdk.weathermap.network

import com.sdk.weathermap.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") query: String,
        @Query("units") units: String = "standart",
        @Query("appid") key: String = "6592d24a33ae13c2ac1401db99732c61"
    ): Response<CurrentWeather>
}