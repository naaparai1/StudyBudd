package com.tmu.studybudd.repo.teacher

import com.tmu.studybudd.model.teacher.TeacherModel

interface TeacherLoginRepo {
    fun validateCredential(email: String, password: String): TeacherModel?
}
