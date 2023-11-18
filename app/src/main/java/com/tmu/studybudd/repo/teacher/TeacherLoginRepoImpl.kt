package com.tmu.studybudd.repo.teacher

import com.tmu.studybudd.fakedb.FakeDB
import com.tmu.studybudd.model.teacher.TeacherModel

object TeacherLoginRepoImpl : TeacherLoginRepo {
    override fun validateCredential(email: String, password: String): TeacherModel? {
        return FakeDB.teachers.firstOrNull {
            it.email == email
        }
    }
}
