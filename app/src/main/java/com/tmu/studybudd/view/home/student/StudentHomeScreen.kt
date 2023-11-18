package com.tmu.studybudd.view.home.student

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.tmu.studybudd.model.student.StudentModel

@Composable
fun StudentHomeScreen(
    student: StudentModel,
    onChangeStatus: () -> Unit = {},
    navigateStudentGroup: () -> Unit = {},
    navigatePractiseQuestion: () -> Unit = {},
    navigateChatBox: () -> Unit = {},
    onScreenChange: (String, Boolean) -> Unit
) {
    LocalContext.current
    onScreenChange(student.name, false)
    Column {
        Text(
            text = "Status: ${student.status}",
            Modifier.clickable {
                onChangeStatus()
            },
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            navigateStudentGroup()
        }) {
            Text(text = "Students and Groups")
        }

        Button(onClick = {
            navigatePractiseQuestion()
        }) {
            Text(text = "Practise question")
        }

        Button(onClick = { navigateChatBox() }) {
            Text(text = "Chat Box")
        }
    }
}
