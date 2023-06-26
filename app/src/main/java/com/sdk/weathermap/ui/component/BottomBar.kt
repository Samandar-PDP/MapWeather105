package com.sdk.weathermap.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val icon: ImageVector,
    val route: String
) {
    object Location: BottomBar(
        icon = Icons.Default.LocationOn,
        route = "location"
    )
    object Favorite: BottomBar(
        icon = Icons.Outlined.Favorite,
        route = "favorite"
    )
    object Settings: BottomBar(
        icon = Icons.Default.Settings,
        route = "settings"
    )
}