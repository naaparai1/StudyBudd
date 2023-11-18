package com.tmu.studybudd.view.home.student.studentgroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StudentProfileScreen(
    studentId: Int,
    onChatClicked: (Int) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<StudentGroupViewModel>()
    viewModel.setCurrentStudent(studentId)
    val student = viewModel.currentStudent
    onScreenChange(student?.name ?: "", true)
    Column {
        Text(text = "Status: ${student?.status}")
        Spacer(modifier = Modifier.size(16.dp))
        if (!viewModel.isSameStudent(studentId)) {
            Button(onClick = { student?.let { onChatClicked(it.id) } }) {
                Text(text = "Chat with ${student?.name}?")
            }
        }
    }
}
