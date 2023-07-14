package com.sdk.weathermap.repository

import com.sdk.weathermap.database.FavoriteDao
import com.sdk.weathermap.database.LocationDao
import com.sdk.weathermap.manager.DataStoreManager
import com.sdk.weathermap.model.CurrentWeather
import com.sdk.weathermap.model.FavoriteEntity
import com.sdk.weathermap.model.LocationName
import com.sdk.weathermap.network.WeatherService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val api: WeatherService,
    private val favoriteDao: FavoriteDao,
    private val dataStoreManager: DataStoreManager
) : WeatherRepository {
    override suspend fun saveLocation(locationName: LocationName) {
        locationDao.saveLocation(locationName)
    }

    override suspend fun deleteLocation(locationName: LocationName) {
        locationDao.deleteLocation(locationName)
    }

    override fun getAllLocations(): Flow<List<LocationName>> {
        return locationDao.getAllLocations()
    }

    override suspend fun updateLocation(id: Int, isSaved: Boolean) {
        locationDao.updateFavLocation(id, isSaved)
    }

    override suspend fun getCurrentWeather(query: String): Flow<CurrentWeather> = flow{
        val response = api.getCurrentWeather(query)
        response.body()?.let {
            emit(it)
        }
    }

    override suspend fun saveFavorite(favoriteEntity: FavoriteEntity) {
         favoriteDao.saveFavorite(favoriteEntity)
    }

    override suspend fun deleteFavorite(name: String) {
        favoriteDao.deleteByName(name)
    }

    override fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun saveTheme(index: Int) {
        dataStoreManager.saveTheme(index)
    }

    override fun getTheme(): Flow<Int> {
        return dataStoreManager.getTheme()
    }
}