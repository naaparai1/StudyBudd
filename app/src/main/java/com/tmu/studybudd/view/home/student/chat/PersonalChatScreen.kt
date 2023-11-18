package com.tmu.studybudd.view.home.student.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PersonalChatScreen(otherStudentId: Int, onScreenChange: (String, Boolean) -> Unit) {
    val viewModel = hiltViewModel<ChatViewModel>()
    viewModel.fetchPersonalChats(otherStudentId)
    val conversations = viewModel.personalChats
    val otherUser = viewModel.otherUser
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    onScreenChange(otherUser, true)
    Column(modifier = Modifier.background(Color(0xFFDBDBDB))) {
        if (conversations.isEmpty()) {
            Text(
                text = "There is no conversation. Start now...",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        LazyColumn(Modifier.weight(1f)) {
            items(conversations) {
                val sender = viewModel.getStudentId(it.senderId)
                if (viewModel.isSameStudent(it.senderId)) {
                    SenderMessage(text = it.message, timeStamp = it.timeStamp)
                    Spacer(modifier = Modifier.size(8.dp))
                } else {
                    ReceiverMessage(
                        sender = sender?.name ?: "",
                        text = it.message,
                        timeStamp = it.timeStamp
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text("Enter text") },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {
                viewModel.onPersonalChatSend(text, otherStudentId)
                text = ""
            }) {
                Text(text = "Send")
            }
        }
    }
}
