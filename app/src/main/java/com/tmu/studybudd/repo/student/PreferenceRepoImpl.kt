package com.tmu.studybudd.repo.student

import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel

object PreferenceRepoImpl : PreferenceRepo {
    private var student: StudentModel? = null
    private var courseId: String = ""
    private var status: String = "Hi there!!!"
    private var teacher: TeacherModel? = null
    override fun getStudent() = student

    override fun saveStudent(student: StudentModel) {
        PreferenceRepoImpl.student = student
    }

    override fun getCourseId() = courseId

    override fun saveCourseId(courseId: String) {
        PreferenceRepoImpl.courseId = courseId
    }

    override fun getStatus() = status

    override fun saveStatus(status: String) {
        PreferenceRepoImpl.status = status
    }

    override fun getTeacher() = teacher

    override fun saveTeacher(teacher: TeacherModel) {
        PreferenceRepoImpl.teacher = PreferenceRepoImpl.teacher
    }
}
