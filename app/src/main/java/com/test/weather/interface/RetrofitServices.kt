package com.test.weather.`interface`

import com.test.weather.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitServices {
    @Headers("X-Yandex-API-Key: ec8428d7-15db-47a7-8313-835c4b1b8403")
    @GET("v2/forecast")
    fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Call<Weather>
}