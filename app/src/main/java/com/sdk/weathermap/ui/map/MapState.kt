package com.sdk.weathermap.ui.map

import com.google.android.gms.maps.model.LatLng

data class MapState(
    val query: String = "",
    val currentLocation: LatLng = LatLng(41.3775, 64.5853),
    val isSomeUIsVisible: Boolean = true,
    val zoom: Float = 4f
)