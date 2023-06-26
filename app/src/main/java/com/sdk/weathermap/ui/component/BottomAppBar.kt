package com.sdk.weathermap.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomAppBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBar.Location,
        BottomBar.Favorite,
        BottomBar.Settings
    )
    val currentDestination by navHostController.currentBackStackEntryAsState()
    val isBottomBarVisible = screens.any { it.route == currentDestination?.destination?.route }

    if (isBottomBarVisible) {
        NavigationBar {
            screens.forEach { screen ->
                NavigationBarItem(
                    selected = screen.route == currentDestination?.destination?.route,
                    onClick = {
                        navHostController.navigate(screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = "btn_icon"
                        )
                    }
                )
            }
        }
    }
}