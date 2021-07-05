package com.test.weather

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class WeatherViewModel() : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: WeatherRepository = WeatherRepository(ApiFactory.weatherApi)

    val weatherLiveData = MutableLiveData<WeatherResponse>()

    fun fetchWeather(lat: Double, lon: Double) {
        scope.launch {
            val weather = repository.getWeatherByCoords(lat, lon)!!
            weatherLiveData.postValue(weather)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}