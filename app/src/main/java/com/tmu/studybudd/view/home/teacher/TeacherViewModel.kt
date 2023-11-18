package com.tmu.studybudd.view.home.teacher

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.repo.teacher.TeacherLoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(private val teacherLoginRepo: TeacherLoginRepo) :
    ViewModel() {
    var teacher by mutableStateOf<TeacherModel?>(null)
    fun validateCredential(email: String, password: String) {
        teacher = teacherLoginRepo.validateCredential(email, password)
    }
}
