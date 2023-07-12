package com.sdk.weathermap.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdk.weathermap.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavorite(favoriteEntity: FavoriteEntity)

    @Query("delete from FavoriteEntity where name = :name")
    suspend fun deleteByName(name: String)

    @Query("select * from FavoriteEntity order by id desc")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>
}