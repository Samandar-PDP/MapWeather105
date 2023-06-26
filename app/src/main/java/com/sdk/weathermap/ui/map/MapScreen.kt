package com.sdk.weathermap.ui.map

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navHostController: NavHostController) {
    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = true
        )
    }
    Scaffold { padding ->
        GoogleMap(
            modifier = Modifier.fillMaxSize().padding(padding),
            uiSettings = uiSettings,
            properties = MapProperties(
                mapStyleOptions = if (isSystemInDarkTheme()) MapStyleOptions(
                    MapStyle.darkMap
                ) else
                    null
            ),
            onMapClick = {
                println("@@@$it")
            }
        )
    }
}