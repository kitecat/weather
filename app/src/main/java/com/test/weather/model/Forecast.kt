package com.test.weather.model

data class Forecast(
    val date: String? = null,
    val date_ts: Long? = null,
    val week: Int? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val moon_code: Int? = null,
    val moon_text: String? = null,
    val parts: Parts? = null
)