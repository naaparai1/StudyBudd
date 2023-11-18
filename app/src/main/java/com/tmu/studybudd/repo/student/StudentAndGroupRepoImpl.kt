package com.tmu.studybudd.repo.student

import com.tmu.studybudd.fakedb.FakeDB
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.StudentModel

object StudentAndGroupRepoImpl : StudentAndGroupRepo {
    override fun getStudents() = FakeDB.students
    override fun getStudentById(id: Int): StudentModel? {
        return FakeDB.students.firstOrNull() { it.id == id }
    }

    override fun getGroups() = FakeDB.groups

    override fun getGroupById(id: Int): GroupModel? {
        return FakeDB.groups.firstOrNull() { it.id == id }
    }
}
