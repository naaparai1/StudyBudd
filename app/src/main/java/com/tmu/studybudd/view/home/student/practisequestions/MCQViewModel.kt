package com.tmu.studybudd.view.home.student.practisequestions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tmu.studybudd.model.common.mcq.MCQs
import com.tmu.studybudd.model.common.mcq.Question
import com.tmu.studybudd.repo.mcq.MCQRepo
import com.tmu.studybudd.repo.student.PreferenceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MCQViewModel @Inject constructor(
    private val mcqRepo: MCQRepo,
    preferenceRepo: PreferenceRepo
) : ViewModel() {
    var mcqs by mutableStateOf<List<MCQs>>(emptyList())
    val courseId = preferenceRepo.getCourseId()
    var questions by mutableStateOf<List<Question>>(emptyList())

    init {
        fetchMCQs()
    }

    private fun fetchMCQs() {
        mcqs = mcqRepo.getMCQs(courseId)
    }

    fun getQuestionsByTopic(topic: String) {
        mcqRepo.getQuestionsByTopic(topic)?.let {
            questions = it
        }
    }
}
