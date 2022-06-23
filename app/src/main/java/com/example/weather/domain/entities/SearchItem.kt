package com.example.weather.domain.entities

data class SearchItem(
    val id: Int = -1,
    val payload: Any? = null,
    val title: String = "",
    val locationType: String = "",
)
