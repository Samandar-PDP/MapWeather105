package com.sdk.weathermap.ui.bottom.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    vm: FavoriteViewModel = hiltViewModel()
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = vm.favoriteList,
            key = { it.id }
        ) {
            Text(text = it.name, fontSize = 25.sp)
            Divider()
        }
    }
}