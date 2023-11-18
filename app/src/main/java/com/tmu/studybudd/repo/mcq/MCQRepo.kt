package com.tmu.studybudd.repo.mcq

import com.tmu.studybudd.model.common.mcq.MCQs
import com.tmu.studybudd.model.common.mcq.Question

interface MCQRepo {
    fun getMCQs(courseId: String): List<MCQs>
    fun getQuestionsByTopic(topic: String): List<Question>?
}
