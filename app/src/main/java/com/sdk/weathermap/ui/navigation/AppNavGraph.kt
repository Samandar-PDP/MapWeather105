package com.sdk.weathermap.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sdk.weathermap.ui.bottom.location.LocationScreen
import com.sdk.weathermap.ui.component.BottomBar
import com.sdk.weathermap.ui.map.MapScreen
import com.sdk.weathermap.util.Graph

@Composable
fun MainGraph(
    modifier: Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        startDestination = BottomBar.Location.route,
        navController = navHostController
    ) {
        composable(route = BottomBar.Location.route) {
            LocationScreen()
        }
        composable(route = BottomBar.Favorite.route) {
            Text(text = "Favorite")
        }
        composable(route = BottomBar.Settings.route) {
            Text(text = "Settings")
        }
        mapGraph(navHostController)
    }
}

fun NavGraphBuilder.mapGraph(navHostController: NavHostController) {
    navigation(
        startDestination = "map_screen",
        route = Graph.MAP
    ) {
        composable(route=  "map_screen") {
            MapScreen(navHostController = navHostController)
        }
    }
}