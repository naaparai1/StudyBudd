package com.tmu.studybudd.view.home.student.studentgroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.StudentModel

@Composable
fun GroupScreen(
    groupId: Int,
    onGroupChatClicked: (GroupModel) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<StudentGroupViewModel>()
    viewModel.setCurrentGroup(groupId)
    val group = viewModel.currentGroup
    onScreenChange(group?.name ?: "", true)
    Column {
        Text(text = "Status: ${group?.status}")
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            group?.let { onGroupChatClicked(it) }
        }) {
            Text(text = "Chat with ${group?.name}?")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Group members: ", fontSize = 17.sp)
        Spacer(modifier = Modifier.size(16.dp))
        GroupMember(list = group?.students ?: emptyList(), modifier = Modifier.weight(1f))
    }
}

@Composable
fun GroupMember(list: List<StudentModel>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(list) {
            Text(text = it.name)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}
