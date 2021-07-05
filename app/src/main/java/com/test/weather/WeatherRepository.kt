package com.test.weather

class WeatherRepository(private val api: WeatherApi) : BaseRepository() {

    suspend fun getWeatherByCoords(lat: Double, lon: Double): WeatherResponse? {

        val movieResponse = safeApiCall(
            call = { api.getWeatherByCoords(lat, lon).await() },
            errorMessage = "Error Fetching"
        )

        return movieResponse
    }
}