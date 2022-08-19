package com.example.weather.presentation.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.weather.R
import com.example.weather.data.network.modelsForecast.City

class SearchAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities: List<City>
) :
    ArrayAdapter<City>(mContext, mLayoutResourceId, cities) {
    private val city: MutableList<City> = ArrayList(cities)

    override fun getCount(): Int {
        return city.size
        Log.d("TAG", "$city.size")
    }
    override fun getItem(position: Int): City {
        return city[position]
        Log.d("TAG", "$city[position]")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            convertView = inflater.inflate(mLayoutResourceId, parent, false)
        }
        try {
            val city: City = getItem(position)
            val autocomplete = convertView!!.findViewById<View>(R.id.autocomplete) as TextView
            autocomplete.text = city.name
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }
}




