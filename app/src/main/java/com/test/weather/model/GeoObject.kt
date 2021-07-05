package com.test.weather.model

data class GeoObject(
    val district: District? = null,
    val locality: Locality? = null,
    val province: Province? = null,
    val country: Country? = null
)