package com.sdk.weathermap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationName(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val isSaved: Boolean,
    val savedDate: String
)