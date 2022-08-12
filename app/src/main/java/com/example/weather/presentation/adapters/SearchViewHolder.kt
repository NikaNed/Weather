package com.example.weather.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.databinding.ItemSearchBinding

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSearchBinding.bind(view)

    fun bind(item: City) = with(binding) {

        tvTitle.text = item.name
    }
}
