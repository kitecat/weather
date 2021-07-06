package com.test.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.weather.R
import com.test.weather.model.Forecast
import com.test.weather.model.ItemViewModel

class WeeklyForecastsFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weekly_forecasts_fragment, container, false)

        view.findViewById<TextView>(R.id.wfCityNameTextView).text =
            viewModel.selectedItem.value?.geo_object?.locality?.name

        val forecasts: List<Forecast>? = viewModel.selectedItem.value?.forecasts

        val day0TempTextView = view.findViewById<TextView>(R.id.day0TempTextView)
        val day1TempTextView = view.findViewById<TextView>(R.id.day1TempTextView)
        val day2TempTextView = view.findViewById<TextView>(R.id.day2TempTextView)
        val day3TempTextView = view.findViewById<TextView>(R.id.day3TempTextView)
        val day4TempTextView = view.findViewById<TextView>(R.id.day4TempTextView)
        val day5TempTextView = view.findViewById<TextView>(R.id.day5TempTextView)
        val day6TempTextView = view.findViewById<TextView>(R.id.day6TempTextView)

        day0TempTextView.text = "" + forecasts?.get(0)?.parts?.day?.temp_avg
        day1TempTextView.text = "" + forecasts?.get(1)?.parts?.day?.temp_avg
        day2TempTextView.text = "" + forecasts?.get(2)?.parts?.day?.temp_avg
        day3TempTextView.text = "" + forecasts?.get(3)?.parts?.day?.temp_avg
        day4TempTextView.text = "" + forecasts?.get(4)?.parts?.day?.temp_avg
        day5TempTextView.text = "" + forecasts?.get(5)?.parts?.day?.temp_avg
        day6TempTextView.text = "" + forecasts?.get(6)?.parts?.day?.temp_avg

        return view
    }
}