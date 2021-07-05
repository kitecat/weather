package com.test.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherViewModel = ViewModelProvider(this, WeatherFactory())
            .get(WeatherViewModel::class.java)

        weatherViewModel.fetchWeather(55.755819, 37.617644)


        weatherViewModel.weatherLiveData.value?.fact?.let { println(it.temp) }

        val citiesNames = arrayOf("Москва", "Санкт-Петербург", "Якутск")
        val citiesLats = arrayOf(55.755819, 59.939099, 62.027221) // Широты городов
        val citiesLons = arrayOf(37.617644, 30.315877, 129.732178) // ДолгОты городов

        var recyclerView: RecyclerView = findViewById(R.id.citiesRecyclerView)
        var cities: Array<City>

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        var jsonAdapter: JsonAdapter<City>

        for (i in citiesNames.indices) {

        }
    }
}

data class TZInfo(
    val offset: Int,
    val name: String,
    val abbr: String,
    val dst: Boolean
)

data class Info(
    val lat: Double,
    val lon: Double,
    val tzInfo: TZInfo,
    val def_pressure_mm: Int,
    val def_pressure_pa: Int,
    val url: String
)

data class Fact(
    val temp: Int,
    val feels_like: Int,
    val icon: String,
    val condition: String,
    val wind_speed: Double,
    val wind_gust: Double,
    val wind_dir: String,
    val pressure_mm: Int,
    val pressure_pa: Int,
    val humidity: Int,
    val daytime: String,
    val polar: Boolean,
    val season: String,
    val obs_time: Long,
    val is_thunder: Boolean,
    val prec_type: Int,
    val prec_strength: Double,
    val cloudness: Double,
    val phenom_icon: String,
    val phenom_condition: String
)

data class Forecasts(
    val date: String,
    val date_ts: Long,
    val week: Int,
    val sunrise: String,
    val sunset: String,
    val moon_code: Int,
    val moon_text: String
)

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val now: Long,
    val now_dt: String,
    val info: Info,
    val fact: Fact,
    val forecasts: Forecasts
)

interface WeatherApi {
    @GET("{lat}&{lon}")
    fun getWeatherByCoords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Deferred<Response<WeatherResponse>>
}