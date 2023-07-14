package com.sdk.weathermap.repository

import com.sdk.weathermap.model.CurrentWeather
import com.sdk.weathermap.model.FavoriteEntity
import com.sdk.weathermap.model.LocationName
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun saveLocation(locationName: LocationName)
    suspend fun deleteLocation(locationName: LocationName)
    fun getAllLocations(): Flow<List<LocationName>>
    suspend fun updateLocation(id: Int, isSaved: Boolean)
    suspend fun getCurrentWeather(query: String): Flow<CurrentWeather>

    suspend fun saveFavorite(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavorite(name: String)
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    suspend fun saveTheme(index: Int)
    fun getTheme(): Flow<Int>
}