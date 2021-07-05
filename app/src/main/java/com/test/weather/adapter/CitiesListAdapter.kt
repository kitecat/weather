package com.test.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.model.Weather

class CitiesListAdapter(
    private val context: Context,
    private val weatherList: MutableList<Weather>
    ) : RecyclerView.Adapter<CitiesListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var cityNameTextView: TextView? = null
        var temperatureTextView: TextView? = null

        init {
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view)
            temperatureTextView = itemView.findViewById(R.id.temperature_text_view)
        }

        fun bind (listItem: Weather) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${listItem.geo_object?.locality?.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cities_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = weatherList[position]
        holder.bind(listItem)

        holder.cityNameTextView?.text = weatherList[position].geo_object?.locality?.name
        holder.temperatureTextView?.text = context.getString(R.string.blank, weatherList[position].fact?.temp)
    }
}