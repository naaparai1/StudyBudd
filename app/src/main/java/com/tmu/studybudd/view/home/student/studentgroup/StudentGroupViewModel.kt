package com.tmu.studybudd.view.home.student.studentgroup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.repo.student.PreferenceRepo
import com.tmu.studybudd.repo.student.StudentAndGroupRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentGroupViewModel @Inject constructor(
    private val studentGroupRepo: StudentAndGroupRepo,
    private val preferenceRepo: PreferenceRepo
) : ViewModel() {
    var currentStudent by mutableStateOf<StudentModel?>(null)
    var currentGroup by mutableStateOf<GroupModel?>(null)
    var students by mutableStateOf<List<StudentModel>>(emptyList())
    var groups by mutableStateOf<List<GroupModel>>(emptyList())

    init {
        getStudents()
        getGroups()
    }

    fun setCurrentStudent(id: Int) {
        currentStudent = studentGroupRepo.getStudentById(id)
    }

    fun setCurrentGroup(id: Int) {
        currentGroup = studentGroupRepo.getGroupById(id)
    }

    private fun getStudents() {
        students = studentGroupRepo.getStudents()
    }

    private fun getGroups() {
        groups = studentGroupRepo.getGroups()
    }

    fun isSameStudent(id: Int): Boolean {
        return id == preferenceRepo.getStudent()?.id
    }
}
