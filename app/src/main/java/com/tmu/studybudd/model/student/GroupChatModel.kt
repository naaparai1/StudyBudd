package com.tmu.studybudd.model.student

data class GroupChatModel(
    val id: Int = 0,
    val groupId: Int,
    val senderId: Int,
    val message: String,
    val timeStamp: Long
)
