package com.test.weather.model

data class Fact(
    val temp: Int? = null,
    val feels_like: Int? = null,
    val icon: String? = null,
    val condition: String? = null,
    val wind_speed: Double? = null,
    val wind_gust: Double? = null,
    val wind_dir: String? = null,
    val pressure_mm: Int? = null,
    val pressure_pa: Int? = null,
    val humidity: Int? = null,
    val daytime: String? = null,
    val polar: Boolean? = null,
    val season: String? = null,
    val prec_type: Int? = null,
    val prec_strength: Double? = null,
    val is_thunder: Boolean? = null,
    val cloudness: Double? = null,
    val obs_time: Long? = null,
    val phenom_icon: String? = null,
    val phenom_condition: String? = null
)