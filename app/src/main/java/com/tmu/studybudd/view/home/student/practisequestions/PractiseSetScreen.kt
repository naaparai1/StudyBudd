package com.tmu.studybudd.view.home.student.practisequestions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmu.studybudd.model.common.mcq.MCQs

@Composable
fun PractiseQuestionsSetScreen(
    onMCQClicked: (String) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<MCQViewModel>()
    val mcqs = viewModel.mcqs
    onScreenChange("Practise Questions", true)
    Column {
        Text(text = "Practise Questions for ${viewModel.courseId}", fontSize = 23.sp)
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn {
            items(mcqs) {
                PractiseSetItem(it) { topic ->
                    onMCQClicked(topic)
                }
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Composable
fun PractiseSetItem(mcq: MCQs, onMCQClicked: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
            .clickable { onMCQClicked(mcq.topic) }
    ) {
        Text(text = mcq.topic, fontSize = 17.sp, modifier = Modifier.padding(16.dp))
    }
}
