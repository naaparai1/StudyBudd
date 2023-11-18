package com.tmu.studybudd.fake

import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.repo.student.PreferenceRepo

class PreferenceRepoFake : PreferenceRepo {
    var isSaveStatusCalled = false
    var isSaveCourseIdCalled = false
    var isSaveStudentCalled = false
    override fun getStudent(): StudentModel? {
        return null
    }

    override fun saveStudent(studentModel: StudentModel) {
        isSaveStudentCalled = true
    }

    override fun getCourseId(): String {
        return "CS1234"
    }

    override fun saveCourseId(courseId: String) {
        isSaveCourseIdCalled = true
    }

    override fun getStatus(): String {
        return ""
    }

    override fun saveStatus(status: String) {
        isSaveStatusCalled = true
    }

    override fun getTeacher(): TeacherModel? {
        return null
    }

    override fun saveTeacher(teacher: TeacherModel) {
    }
}
