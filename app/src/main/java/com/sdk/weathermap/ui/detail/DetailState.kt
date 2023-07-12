package com.sdk.weathermap.ui.detail

import com.sdk.weathermap.model.CurrentWeather

data class DetailState(
    val isLoading: Boolean = false,
    val success: CurrentWeather? = null,
    val error: String = ""
)