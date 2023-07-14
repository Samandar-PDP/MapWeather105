package com.sdk.weathermap.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sdk.weathermap.ui.bottom.favorite.FavoriteScreen
import com.sdk.weathermap.ui.bottom.location.LocationScreen
import com.sdk.weathermap.ui.component.BottomBar
import com.sdk.weathermap.ui.detail.DetailScreen
import com.sdk.weathermap.ui.map.MapDetailScreen
import com.sdk.weathermap.ui.map.MapScreen
import com.sdk.weathermap.ui.map.MapViewModel
import com.sdk.weathermap.ui.settings.SettingScreen
import com.sdk.weathermap.util.Graph

@Composable
fun MainGraph(
    modifier: Modifier,
    navHostController: NavHostController,
    isDark: Boolean
) {
    NavHost(
        modifier = modifier,
        startDestination = BottomBar.Location.route,
        navController = navHostController
    ) {
        composable(route = BottomBar.Location.route) {
            LocationScreen(navHostController = navHostController)
        }
        composable(route = BottomBar.Favorite.route) {
          FavoriteScreen(navHostController = navHostController)
        }
        composable(route = BottomBar.Settings.route) {
            SettingScreen()
        }
        mapGraph(navHostController, isDark)
        detailGraph(navHostController)
    }
}

fun NavGraphBuilder.mapGraph(navHostController: NavHostController, isDark: Boolean) {
    navigation(
        startDestination = "map_screen",
        route = Graph.MAP
    ) {
        composable(route= "map_screen") {
            MapScreen(navHostController = navHostController, isDark = isDark)
        }
        composable(route = "map_detail") {
            MapDetailScreen(navHostController = navHostController)
        }
    }
}

fun NavGraphBuilder.detailGraph(navHostController: NavHostController){
    navigation(
        route = "${Graph.DETAIL}/{id}/{name}/{is_view}",
        startDestination = "detail_screen"
    ) {
        composable(
            route = "detail_screen",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "name"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "is_view"
                ) {
                    type = NavType.StringType
                },
            )
        ) {
            val id = it.arguments?.getString("id") ?: "0"
            val name = it.arguments?.getString("name") ?: "Andijan"
            val isView = it.arguments?.getString("is_view") ?: "false"
            DetailScreen(navHostController = navHostController, id = id.toInt(), name = name,isView = isView.toBoolean())
        }
    }
}