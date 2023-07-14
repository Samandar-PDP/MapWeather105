package com.sdk.weathermap.ui.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.weathermap.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    var theme = mutableStateOf(0)

    init {
        viewModelScope.launch {
            repository.getTheme().collectLatest {
                theme.value = it
            }
        }
    }
    fun saveTheme(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveTheme(index)
        }
    }
}