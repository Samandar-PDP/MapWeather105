package com.sdk.weathermap.ui.bottom.favorite

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.weathermap.model.FavoriteEntity
import com.sdk.weathermap.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    val favoriteList = mutableStateListOf<FavoriteEntity>()

    init {
        viewModelScope.launch {
            repository.getAllFavorites().collectLatest {
                favoriteList.clear()
                favoriteList.addAll(it)
            }
        }
    }
}