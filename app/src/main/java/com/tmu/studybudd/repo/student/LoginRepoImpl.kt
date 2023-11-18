package com.tmu.studybudd.repo.student

import com.tmu.studybudd.fakedb.FakeDB
import com.tmu.studybudd.model.student.StudentModel

object LoginRepoImpl : StudentLoginRepo {
    override fun isStudentExist(email: String, password: String): Boolean {
        val student = FakeDB.students.firstOrNull {
            it.email == email
        }
        return student != null
    }

    override fun getValidStudent(email: String, password: String): StudentModel? {
        return FakeDB.students.firstOrNull() {
            it.email == email
        }
    }
}
