package com.test.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.model.Weather

class CitiesListAdapter(
    private val weatherList: MutableList<Weather>,
    private val onItemClicked: (Weather) -> Unit
) : RecyclerView.Adapter<CitiesListAdapter.ViewHolder>() {

    var data: List<Weather> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View, onItemClicked: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var cityNameTextView: TextView? = null
        var temperatureTextView: TextView? = null

        init {
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view)
            temperatureTextView = itemView.findViewById(R.id.temperature_text_view)

            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(listItem: Weather) {
            cityNameTextView?.text = listItem.geo_object?.locality?.name
            temperatureTextView?.text = listItem.fact?.temp.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cities_list_item, parent, false)
        return ViewHolder(viewHolder) {
            onItemClicked(weatherList[it])
        }
    }

    override fun getItemCount() = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = weatherList[position]
        holder.bind(listItem)
        holder.itemView.setOnClickListener { onItemClicked(listItem) }
    }
}