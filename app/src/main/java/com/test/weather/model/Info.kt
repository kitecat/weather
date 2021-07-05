package com.test.weather.model

data class Info(
    val lat: Double? = null,
    val lon: Double? = null,
    val tzinfo: TZInfo? = null,
    val def_pressure_mm: Int? = null,
    val def_pressure_pa: Int? = null,
    val url: String? = null
)