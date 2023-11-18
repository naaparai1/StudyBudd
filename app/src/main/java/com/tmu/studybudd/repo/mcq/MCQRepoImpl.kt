package com.tmu.studybudd.repo.mcq

import com.tmu.studybudd.fakedb.FakeDB
import com.tmu.studybudd.model.common.mcq.Question

object MCQRepoImpl : MCQRepo {
    override fun getMCQs(courseId: String) = FakeDB.mcqs

    override fun getQuestionsByTopic(topic: String): List<Question>? {
        val mcqs = FakeDB.mcqs.firstOrNull {
            it.topic == topic
        }
        return mcqs?.questions
    }
}
