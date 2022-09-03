package com.example.weather.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val lat: Double,
    val lon: Double,
    val country: String,
    val id: Int,
    val name: String,
    val timezone: Int
): Parcelable
