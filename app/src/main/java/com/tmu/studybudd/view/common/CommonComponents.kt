package com.tmu.studybudd.view.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContainer(
    content: @Composable ((String, Boolean) -> Unit) -> Unit,
    onBackClick: () -> Unit

) {
    var title by remember { mutableStateOf("") }
    var showBackButton by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },

                navigationIcon = {
                    if (showBackButton) {
                        IconButton(
                            onClick = {
                                onBackClick()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    }
                }
            )
        },
        content = {
            // Your screen content goes here
            Column(
                modifier = Modifier.wrapContentHeight()
                    .padding(it).fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content { t, s ->
                    title = t
                    showBackButton = s
                }
            }
        }
    )
}
