package com.sdk.weathermap.ui.detail

import com.sdk.weathermap.model.LocationName

sealed interface DetailEvent {
    data class OnLoadWeather(val query: String): DetailEvent
    data class OnDeleteWeather(val locationName: LocationName): DetailEvent
}