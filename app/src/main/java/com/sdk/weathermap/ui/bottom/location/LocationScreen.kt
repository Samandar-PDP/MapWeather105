package com.sdk.weathermap.ui.bottom.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.weathermap.model.LocationName
import com.sdk.weathermap.util.Graph

@Composable
fun LocationScreen(
    viewModel: LocationViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(
            items = viewModel.locationList,
            key = { it.id }
        ) {
            LocationItem(
                locationName = it,
                onItemClick = { l ->
                    navHostController.navigate("${Graph.DETAIL}/${it.id}/${l.name}/true")
                },
                onFavoriteClick = { l ->
                    viewModel.changeFavorite(l)
                }
            )
        }
    }
}

@Composable
fun LocationItem(
    locationName: LocationName,
    onItemClick: (LocationName) -> Unit,
    onFavoriteClick: (LocationName) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onItemClick(locationName) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = locationName.name,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth(.8f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Column {
            IconButton(onClick = { onFavoriteClick(locationName) }) {
                Icon(
                    imageVector = if (locationName.isSaved) Icons.Default.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "icon",
                    tint = if (locationName.isSaved) Color.Red else Color.Black.copy(alpha = .5f)
                )
            }
            Text(
                text = locationName.savedDate,
                fontSize = 12.sp
            )
        }
    }
}