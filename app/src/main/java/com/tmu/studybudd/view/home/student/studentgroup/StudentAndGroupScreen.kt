package com.tmu.studybudd.view.home.student.studentgroup

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.StudentModel

@Composable
fun StudentAndGroupScreen(
    onStudentClicked: (StudentModel) -> Unit,
    onGroupClicked: (GroupModel) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<StudentGroupViewModel>()
    val students = viewModel.students
    val groups = viewModel.groups
    onScreenChange("Student and Group", true)
    LazyColumn(modifier = Modifier.background(Color(0xFFDBDBDB))) {
        item {
            Text(
                text = "Students",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        if (students.isEmpty()) {
            item {
                Text(text = "There is no student  available at the moment")
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        items(students) {
            StudentItem(it) { student ->
                onStudentClicked(student)
            }
            Spacer(modifier = Modifier.size(4.dp))
        }

        item {
            Text(
                text = "Groups",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        if (groups.isEmpty()) {
            item {
                Text(text = "There is no group  available at the moment")
            }
        }
        items(groups) {
            GroupItem(it) { group ->
                onGroupClicked(group)
            }
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
private fun StudentItem(
    student: StudentModel,
    onStudentClick: (StudentModel) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color(0xFF8BC34A))
            .clickable { onStudentClick(student) }
    ) {
        Text(text = student.name)
        Text(text = student.status)
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun GroupItem(
    group: GroupModel,
    onGroupClick: (GroupModel) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFFF968E))
            .clickable { onGroupClick(group) }
    ) {
        Text(text = group.name)
        Text(text = group.status)
        Spacer(modifier = Modifier.size(8.dp))
    }
}
