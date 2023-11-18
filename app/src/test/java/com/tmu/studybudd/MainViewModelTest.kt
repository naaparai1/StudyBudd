package com.tmu.studybudd

import com.tmu.studybudd.fake.PreferenceRepoFake
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private val preferenceRepo = PreferenceRepoFake()

    // given
    // when
    // then
    @Before
    fun `set up`() {
        viewModel = MainViewModel(preferenceRepo)
    }

    @Test
    fun `test add function with positive values`() {
        // given
        val a = 5
        val b = 15
        // when
        val sum = viewModel.add(a, b)
        // then
        Assert.assertEquals(20, sum)
    }

    @Test
    fun `test add function with negative value`() {
        // given
        val a = -5
        val b = -15
        // when
        val sum = viewModel.add(a, b)
        // then
        Assert.assertEquals(-20, sum)
    }

    @Test
    fun `test add function with one negative one positive value`() {
        // given
        val a = -5
        val b = 10
        // when
        val sum = viewModel.add(a, b)
        // then
        Assert.assertEquals(5, sum)
    }

    @After
    fun `tear down`() {
        // nothing to do
    }

    @Test
    fun `test save status`() {
        // given
        val status = "I am ready"
        // when
        viewModel.saveStatus(status)
        // then
        Assert.assertEquals(viewModel.status, status)
        Assert.assertEquals(true, preferenceRepo.isSaveStatusCalled)
    }

    @Test
    fun `test saveCourseId`() {
        // given
        val courseId = "123456"
        // when
        viewModel.saveCourseId(courseId)
        // then
        Assert.assertEquals(courseId, viewModel.courseId)
        Assert.assertEquals(true, preferenceRepo.isSaveCourseIdCalled)
    }
}
