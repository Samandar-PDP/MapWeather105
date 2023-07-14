package com.sdk.weathermap.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore("my_pref")

    companion object {
        private val THEME = intPreferencesKey("theme")
    }

    suspend fun saveTheme(index: Int) {
        context.dataStore.edit {
            it[THEME] = index
        }
    }

    fun getTheme(): Flow<Int> = context.dataStore.data.map {
        it[THEME] ?: 0
    }
}