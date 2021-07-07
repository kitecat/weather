package com.test.weather.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.`interface`.RetrofitServices
import com.test.weather.adapter.CitiesListAdapter
import com.test.weather.common.Common
import com.test.weather.model.ItemViewModel
import com.test.weather.model.Weather
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesListFragment : Fragment() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CitiesListAdapter
    lateinit var dialog: AlertDialog
    lateinit var recyclerView: RecyclerView

    private val citiesLats = arrayOf(55.755819, 59.939099, 62.027221) // Широты городов
    private val citiesLons = arrayOf(37.617644, 30.315877, 129.732178) // ДолгОты городов
    private var weatherList = mutableListOf<Weather>() // Погода в городах

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mService = Common.retrofitService
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(context).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cities_list_fragment, container, false)

        recyclerView = view.findViewById(R.id.citiesRecyclerView)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val citiesCount: Int = citiesLats.size - 1
        for (i in 0..citiesCount) {
            getWeatherList(citiesLats[i], citiesLons[i])
        }
        dialog.show()

        return view
    }

    private fun getWeatherList(lat: Double, lon: Double) {

        mService.getWeather(lat, lon).enqueue(object : Callback<Weather> {

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (weatherList.size > 2) {
                    weatherList.clear()
                }
                weatherList.add(response.body() as Weather)
                applyDataAndShow()
            }
        })
    }

    private fun applyDataAndShow() {
        if (weatherList.size == citiesLats.size) {
            adapter = CitiesListAdapter(weatherList) {
                viewModel.selectItem(it)
            }
            adapter.notifyDataSetChanged()
            recyclerView.adapter = adapter
            dialog.dismiss()
        }
    }
}