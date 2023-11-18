package com.tmu.studybudd.view.login

import androidx.lifecycle.ViewModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.repo.student.PreferenceRepo
import com.tmu.studybudd.repo.student.StudentLoginRepo
import com.tmu.studybudd.repo.teacher.TeacherLoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val studentLoginRepo: StudentLoginRepo,
    private val teacherLoginRepo: TeacherLoginRepo,
    private val preferenceRepo: PreferenceRepo
) : ViewModel() {
    fun validStudentCredential(email: String, password: String): StudentModel? {
        val studentModel = studentLoginRepo.getValidStudent(email, password)
        studentModel?.let { preferenceRepo.saveStudent(it) }
        return studentModel
    }

    fun validateTeacherCredential(email: String, password: String): TeacherModel? {
        return teacherLoginRepo.validateCredential(email, password)
    }
}
