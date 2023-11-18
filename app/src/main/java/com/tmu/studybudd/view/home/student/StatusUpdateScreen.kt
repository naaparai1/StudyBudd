package com.tmu.studybudd.view.home.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusUpdateScreen(
    status: String,
    onSave: (String) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    var newStatusUpdate by remember { mutableStateOf("") }
    onScreenChange("Status update", true)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Status : $status", fontSize = 17.sp)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Set new Status")
        Spacer(modifier = Modifier.size(8.dp))

        TextField(
            value = newStatusUpdate,
            onValueChange = { newText -> newStatusUpdate = newText },
            label = { androidx.compose.material3.Text("Enter New Status here") },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            textStyle = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(8.dp))

        Button(onClick = { onSave(newStatusUpdate) }) {
            Text(text = "Save")
        }
    }
}
