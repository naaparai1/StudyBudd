package com.tmu.studybudd

import com.tmu.studybudd.fake.PreferenceRepoFake
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.repo.student.StudentLoginRepo
import com.tmu.studybudd.repo.teacher.TeacherLoginRepo
import com.tmu.studybudd.view.login.LoginViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel
    private val preferenceRepo = PreferenceRepoFake()
    private val studentLoginRepo = mockk<StudentLoginRepo>(relaxed = true)
    private val teacherLoginRepo = mockk<TeacherLoginRepo>(relaxed = true)
    lateinit var teacherModel: TeacherModel

    @Before
    fun `set up`() {
        viewModel = LoginViewModel(studentLoginRepo, teacherLoginRepo, preferenceRepo)
    }

    @Test
    fun `test validateTeacherCredential when return not null`() {
        // given
        val email = "123@gmail.com"
        val password = "12345"
        teacherModel = TeacherModel(1, "Sherap", "123@gmail.com", arrayListOf())
        every { teacherLoginRepo.validateCredential(email, password) } returns teacherModel

        // when
        viewModel.validateTeacherCredential("123@gmail.com", "12345")
        // then
        Assert.assertNotNull(teacherModel)
        verify(exactly = 2) { teacherLoginRepo.validateCredential(email, password) }
    }

    @Test
    fun `test validStudentCredential return not null`() {
        // given
        val email = "student@gmail.com"
        val password = "432167"
        val studentModel = StudentModel(1, "Student-1", email = email, "I am online")
        every { studentLoginRepo.getValidStudent(email, password) } returns studentModel

        // when
        val student = viewModel.validStudentCredential(email = email, password = password)

        // then
        Assert.assertEquals("student@gmail.com", student?.email)
        verify { studentLoginRepo.getValidStudent(email, password) }
        Assert.assertTrue(preferenceRepo.isSaveStudentCalled)
    }

    @Test
    fun `test validStudentCredential return null`() {
        // given
        val email = "student@gmail.com"
        val password = "432167"
        every { studentLoginRepo.getValidStudent(email, password) } returns null

        // when
        val student = viewModel.validStudentCredential(email = email, password = password)

        // then
        Assert.assertNull(student)
        verify(exactly = 1) { studentLoginRepo.getValidStudent(email, password) }
        Assert.assertFalse(preferenceRepo.isSaveStudentCalled)
    }
}
