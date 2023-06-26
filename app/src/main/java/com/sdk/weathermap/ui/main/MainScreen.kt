package com.sdk.weathermap.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sdk.weathermap.ui.component.BottomAppBar
import com.sdk.weathermap.ui.navigation.MainGraph
import com.sdk.weathermap.util.Graph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController = rememberNavController()) {
    val current by navHostController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(navHostController = navHostController)
        },
        floatingActionButton = {
            if (current?.destination?.route == "location") {
                FloatingActionButton(onClick = {
                    navHostController.navigate(Graph.MAP)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        MainGraph(
            modifier = Modifier.padding(padding),
            navHostController = navHostController
        )
    }
}