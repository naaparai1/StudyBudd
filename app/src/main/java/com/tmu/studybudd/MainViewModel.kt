package com.tmu.studybudd

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.repo.student.PreferenceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val preference: PreferenceRepo) : ViewModel() {
    var status by mutableStateOf("")
    var courseId by mutableStateOf("")
    var loggedInStudent by mutableStateOf<StudentModel?>(null)
    var loggedInTeacher by mutableStateOf<TeacherModel?>(null)

    init {
        initData()
    }

    private fun initData() {
        loggedInStudent = preference.getStudent()
        courseId = preference.getCourseId()
        status = preference.getStatus()
    }

    fun saveStudent(studentModel: StudentModel) {
        preference.saveStudent(studentModel = studentModel)
        this.loggedInStudent = studentModel
    }

    fun saveTeacher(teacher: TeacherModel) {
        preference.saveTeacher(teacher = teacher)
        this.loggedInTeacher = teacher
    }

    fun saveCourseId(courseId: String) {
        preference.saveCourseId(courseId)
        this.courseId = courseId
    }

    fun saveStatus(status: String) {
        preference.saveStatus(status)
        this.status = status
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }
}
