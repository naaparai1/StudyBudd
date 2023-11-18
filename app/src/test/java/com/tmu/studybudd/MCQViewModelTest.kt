package com.tmu.studybudd

import com.tmu.studybudd.fake.PreferenceRepoFake
import com.tmu.studybudd.model.common.mcq.MCQs
import com.tmu.studybudd.model.common.mcq.Question
import com.tmu.studybudd.repo.mcq.MCQRepo
import com.tmu.studybudd.view.home.student.practisequestions.MCQViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MCQViewModelTest {
    private lateinit var viewModel: MCQViewModel
    private val preferenceRepo = PreferenceRepoFake()

    private val mcqRepo = mockk<MCQRepo>(relaxed = true)
    lateinit var myList: List<MCQs>

    @Before
    fun `set up`() {
        val mcq = MCQs("DBSM", arrayListOf())
        myList = listOf(mcq)
        every { mcqRepo.getMCQs("CS1234") } returns myList
        viewModel = MCQViewModel(mcqRepo, preferenceRepo)
    }

    @Test
    fun `test init`() {
        // given:
        // when: object is created
        // then
        verify { mcqRepo.getMCQs("CS1234") }
        Assert.assertEquals(myList, viewModel.mcqs)
    }

    @Test
    fun `test getQuestionsByTopic when return not null`() {
        // given
        val topic = "DBMS"
        val question = Question("what is dbms")
        val list = listOf(question)
        every { mcqRepo.getQuestionsByTopic(topic) } returns list
        // when
        viewModel.getQuestionsByTopic(topic)
        // then
        Assert.assertEquals(list, viewModel.questions)
    }

    @Test
    fun `test getQuestionsByTopic when return null`() {
        // given
        val topic = "DBMS"
        val list = null
        every { mcqRepo.getQuestionsByTopic(topic) } returns list
        // when
        viewModel.getQuestionsByTopic(topic)
        // then
        Assert.assertTrue(viewModel.questions.isEmpty())
    }
}
