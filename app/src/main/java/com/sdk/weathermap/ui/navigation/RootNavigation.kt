package com.sdk.weathermap.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sdk.weathermap.ui.main.MainScreen
import com.sdk.weathermap.util.Graph

@Composable
fun RootNavigation(navHostController: NavHostController) {
    NavHost(
        route = Graph.ROOT,
        navController = navHostController,
        startDestination = Graph.MAIN,
    ) {
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}