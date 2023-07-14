package com.sdk.weathermap.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.sdk.weathermap.R
import com.sdk.weathermap.model.LocationName
import com.sdk.weathermap.ui.component.DeleteDialog
import com.sdk.weathermap.util.Graph
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    id: Int,
    name: String,
    isView: Boolean,
    vm: DetailViewModel = hiltViewModel()
) {
    var isDeleteDialogOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackBar = remember { SnackbarHostState() }
    LaunchedEffect(key1 = Unit) {
        vm.onEvent(DetailEvent.OnLoadWeather(name))
    }
    val state by vm.state.collectAsState()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBar)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = name)
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                actions = {
                    if (isView) {
                        IconButton(onClick = {
                            isDeleteDialogOpen = true
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "back")
                        }
                    }
                }
            )
        }
    ) { p ->
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        state.success?.let { data ->
            if (isDeleteDialogOpen) {
                DeleteDialog(
                    onDelete = {
                        scope.launch {
                            vm.onEvent(
                                DetailEvent.OnDeleteWeather(
                                    LocationName(
                                        id = id,
                                        name = name,
                                        isSaved = false,
                                        Graph.getCurrentTime()
                                    )
                                )
                            )
                            isDeleteDialogOpen = false
                            snackBar.showSnackbar("Deleted")
                            navHostController.popBackStack()
                        }
                    },
                    onDismiss = { isDeleteDialogOpen = false },
                    text = data.name
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(p)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = Graph.getCurrentTime())
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val painter =
                        rememberCoilPainter(request = Graph.getImageUrl(data.weather[0].icon))
                    Image(
                        painter = painter,
                        contentDescription = "painter",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(text = "${(data.main.temp - 273.15).toInt()} °C", fontSize = 40.sp)
                }
                Text(text = data.weather[0].main, fontSize = 20.sp)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 10.dp)
                )
                RowIconText(icon = R.drawable.wind, text = "${data.wind.speed} m/s")
                RowIconText(icon = R.drawable.hum, text = "${data.main.humidity} %")
                RowIconText(icon = R.drawable.trie, text = "${data.main.pressure} hpa")
            }
        }
    }
}

@Composable
private fun RowIconText(
    icon: Int,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "icon")
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text)
    }
}