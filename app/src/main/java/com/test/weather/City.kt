package com.test.weather

data class City(var name: String, var temp: Int, var weekTemp: IntArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as City

        if (name != other.name) return false
        if (temp != other.temp) return false
        if (!weekTemp.contentEquals(other.weekTemp)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + temp
        result = 31 * result + weekTemp.contentHashCode()
        return result
    }
}