package com.sdk.weathermap.ui.map

import com.google.android.gms.maps.model.LatLng

sealed interface MapEvent {
    object OnSearch: MapEvent
    data class OnMapMoved(val isMoving: Boolean): MapEvent
    data class OnTextChanged(val text: String): MapEvent
    data class OnMapClicked(val latLng: LatLng): MapEvent
    data class OnSaveLocationName(val name: String): MapEvent
}