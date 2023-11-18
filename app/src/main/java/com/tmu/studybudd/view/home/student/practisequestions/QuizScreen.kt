package com.tmu.studybudd.view.home.student.practisequestions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmu.studybudd.R
import com.tmu.studybudd.model.common.mcq.Question

@Composable
fun QuizApp(
    topic: String,
    navigateUp: () -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<MCQViewModel>()

    viewModel.getQuestionsByTopic(topic)
    val questions = viewModel.questions

    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    var progress by remember { mutableFloatStateOf(0.0f) }

    val currentQuestion = questions[currentQuestionIndex]

    onScreenChange(topic, true)
    Scaffold(
        topBar = {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { paadingValue ->
            QuestionScreen(
                question = currentQuestion.question ?: "",
                options = currentQuestion.options ?: emptyList(),
                onAnswerSelected = {
                    // Handle answer selection
                    // For simplicity, just move to the next question
                    if (currentQuestionIndex + 1 == questions.size) {
                        navigateUp()
                    } else {
                        currentQuestionIndex++
                    }
                    progress = (currentQuestionIndex.toFloat() / questions.size.toFloat())
                }
            )
        }
    )
}

@Composable
fun QuestionScreen(
    question: String,
    options: List<String>,
    onAnswerSelected: (Int) -> Unit
) {
    var selectedOption by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Question
        Text(text = question, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Options
        options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                    selectedOption = index
                    onAnswerSelected(index)
                }.background(
                    color = if (index == selectedOption) {
                        Color.Gray.copy(alpha = 0.2f)
                    } else {
                        Color.Transparent
                    }
                )
            ) {
                RadioButton(
                    selected = index == selectedOption,
                    onClick = { selectedOption = index },
                    modifier = Modifier.padding(end = 8.dp).size(24.dp)
                )

                Text(text = option)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun QuestionCard(question: Question) {
    var selectedOption by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = question.question ?: "",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        question.options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier.toggleable(value = index == selectedOption) {
                    selectedOption = if (it) index else -1
                }.fillMaxWidth().padding(8.dp)
            ) {
                RadioButton(
                    selected = index == selectedOption,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(id = R.color.teal_500)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }
    }
}
