package com.test.weather.model

data class Weather(
    val now: Long? = null,
    val now_dt: String? = null,
    val info: Info? = null,
    val geo_object: GeoObject? = null,
    val fact: Fact? = null,
    val forecasts: List<Forecast>? = null
)