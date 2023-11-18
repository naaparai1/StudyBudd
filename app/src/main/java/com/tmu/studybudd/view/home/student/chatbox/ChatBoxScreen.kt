package com.tmu.studybudd.view.home.student.chatbox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.view.home.student.chat.ChatViewModel

@Composable
fun ChatBoxScreen(
    onStudentClicked: (Int) -> Unit,
    onGroupClicked: (Int) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<ChatViewModel>()
    val students = viewModel.personalChatHistory
    val groups = viewModel.groupChatHistory
    onScreenChange("Chat box", true)
    Column {
        LazyColumn {
            item {
                Text(text = "Students: ", fontSize = 17.sp)
                Spacer(modifier = Modifier.size(8.dp))
            }
            if (students.isEmpty()) {
                item {
                    Text(text = "No Chat history yet. Start now...")
                    Spacer(modifier = Modifier.size(8.dp))
                }
            } else {
                items(students) {
                    StudentChatItem(it) { studentModel ->
                        onStudentClicked(studentModel.id)
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            item {
                Text(text = "Groups: ", fontSize = 17.sp)
                Spacer(modifier = Modifier.size(8.dp))
            }
            if (groups.isEmpty()) {
                item {
                    Text(text = "No Chat history yet. Start now...")
                    Spacer(modifier = Modifier.size(8.dp))
                }
            } else {
                items(groups) {
                    GroupChatItem(it) { groupModel ->
                        onGroupClicked(groupModel.id)
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
    }
}

@Composable
fun StudentChatItem(student: StudentModel, onStudentClick: (StudentModel) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color(0xFF8BC34A))
            .clickable { onStudentClick(student) }
    ) {
        Text(text = student.name)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = student.status)
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun GroupChatItem(group: GroupModel, onGroupClick: (GroupModel) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFFF8F86))
            .clickable { onGroupClick(group) }
    ) {
        Text(text = group.name)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = group.status)
        Spacer(modifier = Modifier.size(16.dp))
    }
}
