package com.sdk.weathermap.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sdk.weathermap.ui.map.MapEvent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    modifier: Modifier,
    text: String,
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onValueChanged: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(55.dp),
        shape = ShapeDefaults.Medium
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            placeholder = {
                Text(text = "Address...")
            },
            onValueChange = onValueChanged,
            leadingIcon = {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotBlank()) {
                        onSearch()
                    }
                }
            )
        )
    }
}