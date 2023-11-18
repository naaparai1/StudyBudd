package com.tmu.studybudd.repo.student

import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel

interface PreferenceRepo {
    fun getStudent(): StudentModel?
    fun saveStudent(studentModel: StudentModel)
    fun getCourseId(): String
    fun saveCourseId(courseId: String)
    fun getStatus(): String
    fun saveStatus(status: String)
    fun getTeacher(): TeacherModel?
    fun saveTeacher(teacher: TeacherModel)
}
