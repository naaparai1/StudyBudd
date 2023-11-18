package com.tmu.studybudd.model.student

data class PersonalChatModel(
    val id: Int,
    val senderId: Int,
    val receiverId: Int,
    val message: String,
    val timeStamp: Long
)
