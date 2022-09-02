package com.magdev.presentation.main.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magdev.R
import com.magdev.databinding.ItemWeekDayBinding
import com.magdev.domain.weather.WeatherResponse
import com.magdev.infrastructure.extensions.formatDegree
import com.magdev.infrastructure.extensions.loadWeatherIcon
import java.text.SimpleDateFormat
import java.util.*

class WeekWeatherAdapter : RecyclerView.Adapter<WeekWeatherAdapter.SearchMatchItemHolder>() {

    private var data: MutableList<WeatherResponse.Daily> = mutableListOf()

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchItemHolder {
        val binding = ItemWeekDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchMatchItemHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SearchMatchItemHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<WeatherResponse.Daily>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class SearchMatchItemHolder(private val context: Context,
                                private val binding: ItemWeekDayBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherResponse.Daily) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = (item.dt ?: 0) * 1000L

            binding.weekDayText.text = SimpleDateFormat("EE", Locale.getDefault()).format(calendar.time)
            binding.dateText.text = SimpleDateFormat("dd", Locale.getDefault()).format(calendar.time)
            binding.degText.text = context.getString(R.string.degree_value, (item.temp?.day?.toInt() ?: 0).formatDegree())

            val iconId = item.weather?.firstOrNull()?.icon

            if(iconId != null) {
                binding.weatherIcon.visibility = View.VISIBLE
                binding.weatherIcon.loadWeatherIcon(iconId)
            } else {
                binding.weatherIcon.visibility = View.GONE
            }
        }
    }
}