package com.tmu.studybudd.view.home.teacher

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import com.tmu.studybudd.model.teacher.TeacherModel

@Composable
fun TeacherHomeScreen(
    teacher: TeacherModel,
    onScreenChange: (String, Boolean) -> Unit
) {
    LocalContext.current
    onScreenChange(teacher.name, false)
    Column {
        Text(
            text = "Yet to implement...",
            fontStyle = FontStyle.Italic
        )
    }
}
