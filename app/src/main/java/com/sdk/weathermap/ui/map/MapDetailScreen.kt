package com.sdk.weathermap.ui.map

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.weathermap.MainActivity
import com.sdk.weathermap.util.Graph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapDetailScreen(
    navHostController: NavHostController,
    vm: MapViewModel = hiltViewModel()
) {
    val main = LocalContext.current as MainActivity
    var locationName by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val host = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = host)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Enter location title"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = locationName,
                onValueChange = { locationName = it },
                placeholder = {
                    Text(text = "Location name")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (locationName.isNotBlank()) {
                        scope.launch {
                            vm.onEvent(MapEvent.OnSaveLocationName(locationName))
                            host.showSnackbar("Saved")
                            main.startActivity(Intent(main, MainActivity::class.java))
                            main.finish()
//                            navHostController.navigate(Graph.MAIN) {
//                                popUpTo(Graph.MAIN) {
//                                    inclusive = true
//                                }
//                            }
                        }
                    } else {
                        scope.launch {
                            host.showSnackbar("Enter title")
                        }
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}