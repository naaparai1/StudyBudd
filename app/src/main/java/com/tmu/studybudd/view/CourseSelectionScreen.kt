package com.tmu.studybudd.view

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tmu.studybudd.util.Utils

@Composable
fun CourseSelectionScreen(
    nextScreen: (String) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    onScreenChange("Course Selection", false)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter Course Code")
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("Enter text") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            textStyle = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = {
            if (Utils.isCourseIdValid(text)) {
                nextScreen(text)
            } else {
                Toast.makeText(context, "Enter a valid Code", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Go")
        }
    }
}
