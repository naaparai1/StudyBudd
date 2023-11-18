package com.tmu.studybudd.repo.student

import com.tmu.studybudd.model.student.StudentModel

interface StudentLoginRepo {
    fun isStudentExist(email: String, password: String): Boolean
    fun getValidStudent(email: String, password: String): StudentModel?
}
