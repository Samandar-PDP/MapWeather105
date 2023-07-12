package com.sdk.weathermap.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.weathermap.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state get() = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnLoadWeather -> {
                viewModelScope.launch {
                    repository.getCurrentWeather(event.query)
                        .onStart {
                            _state.update {
                                it.copy(isLoading = true)
                            }
                        }.catch { t ->
                            _state.update {
                                it.copy(isLoading = false, error = t.message.toString())
                            }
                        }.collectLatest { r ->
                            _state.update {
                                it.copy(isLoading = false, success = r)
                            }
                        }
                }
            }
            is DetailEvent.OnDeleteWeather -> {
                viewModelScope.launch {
                    repository.deleteLocation(event.locationName)
                }
            }
        }
    }
}