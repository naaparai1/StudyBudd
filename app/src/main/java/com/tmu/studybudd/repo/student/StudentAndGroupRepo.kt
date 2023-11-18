package com.tmu.studybudd.repo.student

import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.StudentModel

interface StudentAndGroupRepo {
    fun getStudents(): List<StudentModel>
    fun getStudentById(id: Int): StudentModel?
    fun getGroups(): List<GroupModel>
    fun getGroupById(id: Int): GroupModel?
}
