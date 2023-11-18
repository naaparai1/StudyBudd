package com.tmu.studybudd.model.student

data class StudentModel(
    val id: Int = 1,
    val name: String,
    val email: String,
    val status: String
)

data class GroupModel(
    val id: Int = 1,
    val name: String,
    val status: String,
    val students: List<StudentModel>
)
