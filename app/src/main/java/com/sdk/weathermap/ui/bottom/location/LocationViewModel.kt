package com.sdk.weathermap.ui.bottom.location

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.weathermap.model.FavoriteEntity
import com.sdk.weathermap.model.LocationName
import com.sdk.weathermap.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    var locationList = mutableStateListOf<LocationName>()

    init {
        viewModelScope.launch {
            repository.getAllLocations().collectLatest {
                locationList.clear()
                locationList.addAll(it)
            }
        }
    }

    fun changeFavorite(locationName: LocationName) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocation(
                locationName.id,
                !locationName.isSaved
            )
            if (!locationName.isSaved) {
                repository.saveFavorite(FavoriteEntity(name = locationName.name))
            } else {
                repository.deleteFavorite(locationName.name)
            }
        }
    }
}