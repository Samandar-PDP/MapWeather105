package com.sdk.weathermap.di

import android.content.Context
import android.location.Geocoder
import androidx.room.Room
import com.sdk.weathermap.database.FavoriteDao
import com.sdk.weathermap.database.LocationDao
import com.sdk.weathermap.database.WeatherDatabase
import com.sdk.weathermap.manager.DataStoreManager
import com.sdk.weathermap.network.WeatherService
import com.sdk.weathermap.repository.WeatherRepository
import com.sdk.weathermap.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGeoCoder(
        @ApplicationContext context: Context
    ): Geocoder {
        return Geocoder(context)
    }

    @[Provides Singleton]
    fun provideWeatherWeatherDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weather.db"
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideLocationDao(database: WeatherDatabase): LocationDao {
        return database.locDao
    }
    @[Provides Singleton]
    fun provideFavoriteDao(database: WeatherDatabase): FavoriteDao {
        return database.favDao
    }

    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        dao: LocationDao,
        favoriteDao: FavoriteDao,
        weatherService: WeatherService,
        manager: DataStoreManager
    ): WeatherRepository {
        return WeatherRepositoryImpl(dao, weatherService, favoriteDao, manager)
    }
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStoreManager {
        return DataStoreManager(context)
    }
}