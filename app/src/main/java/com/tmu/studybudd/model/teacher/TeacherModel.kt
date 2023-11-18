package com.tmu.studybudd.model.teacher

import com.tmu.studybudd.model.common.course.Course

data class TeacherModel(val id: Int, val name: String, val email: String, val courses: List<Course>)
