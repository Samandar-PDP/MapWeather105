package com.sdk.weathermap.ui.bottom.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.weathermap.util.Graph

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
            Text(text = it.name, fontSize = 20.sp, modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable {
                    navHostController.navigate("${Graph.DETAIL}/${it.id}/${it.name}/false")
                }
            )
            Divider()
        }
    }
}