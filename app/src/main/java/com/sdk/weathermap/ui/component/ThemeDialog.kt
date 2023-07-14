package com.sdk.weathermap.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ThemeDialog(
    onDismiss: () -> Unit,
    onSelect: (Int) -> Unit,
    currentIndex: Int
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Select Theme")
        },
        text = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = currentIndex == 0, onClick = { onSelect(0) })
                    Text(text = "System")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = currentIndex == 1, onClick = { onSelect(1) })
                    Text(text = "Light")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = currentIndex == 2, onClick = { onSelect(2) })
                    Text(text = "Dark")
                }
            }
        },
        confirmButton = {

        }
    )
}