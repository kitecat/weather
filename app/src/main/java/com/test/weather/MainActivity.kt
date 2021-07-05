package com.test.weather

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.`interface`.RetrofitServices
import com.test.weather.adapter.CitiesListAdapter
import com.test.weather.common.Common
import com.test.weather.model.Weather
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CitiesListAdapter
    lateinit var dialog: AlertDialog
    lateinit var recyclerView: RecyclerView

    private val citiesLats = arrayOf(55.755819, 59.939099, 62.027221) // Широты городов
    private val citiesLons = arrayOf(37.617644, 30.315877, 129.732178) // ДолгОты городов
    private var weatherList = mutableListOf<Weather>() // Погода в городах

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService

        recyclerView = findViewById(R.id.citiesRecyclerView)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()
        dialog.show()

        val citiesCount: Int = citiesLats.size - 1
        for (i in 0..citiesCount) {
            getWeatherList(citiesLats[i], citiesLons[i])
        }
    }

    private fun getWeatherList(lat: Double, lon: Double) {

        mService.getWeather(lat, lon).enqueue(object : Callback<Weather> {

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                weatherList.add(response.body() as Weather)
                applyDataAndShow()
            }
        })
    }

    private fun applyDataAndShow() {
        if (weatherList.size == citiesLats.size) {
            adapter = CitiesListAdapter(baseContext, weatherList)
            adapter.notifyDataSetChanged()
            recyclerView.adapter = adapter
            dialog.dismiss()
        }
    }
}