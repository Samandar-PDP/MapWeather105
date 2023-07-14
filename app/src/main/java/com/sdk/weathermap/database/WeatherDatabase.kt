package com.sdk.weathermap.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.weathermap.model.FavoriteEntity
import com.sdk.weathermap.model.LocationName

@Database(entities = [LocationName::class,FavoriteEntity::class], version = 3, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract val locDao: LocationDao
    abstract val favDao: FavoriteDao
}