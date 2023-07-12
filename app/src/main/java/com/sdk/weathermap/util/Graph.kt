package com.sdk.weathermap.util

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val MAP = "map_graph"
    const val DETAIL = "detail_graph"

    fun getImageUrl(image: String): String {
        return "https://openweathermap.org/img/wn/$image@2x.png"
    }
}