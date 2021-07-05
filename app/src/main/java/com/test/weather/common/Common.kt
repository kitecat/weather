package com.test.weather.common

import com.test.weather.`interface`.RetrofitServices
import com.test.weather.retrofit.RetrofitClient


object Common {
    private val BASE_URL = "https://api.weather.yandex.ru/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}