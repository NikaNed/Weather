package com.example.weather.presentation.adapters

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.weather.R
import com.example.weather.data.network.modelsForecast.City


class SearchAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities:List<City>,
) :
    ArrayAdapter<City>(mContext, mLayoutResourceId, cities) {
    private val city: MutableList<City> = ArrayList(cities)

    override fun getCount(): Int {
        return city.size
    }
    override fun getItem(position: Int): City {
        return city[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            convertView = inflater.inflate(R.layout.item_search, parent, false)
        }
        try {
            val city: City = getItem(position)
            val cityAutoCompleteView = convertView!!.findViewById<View>(R.id.tvTitle) as TextView
            cityAutoCompleteView.text = city.name
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }
}

