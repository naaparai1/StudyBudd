package com.tmu.studybudd.repo.student

import com.tmu.studybudd.model.student.GroupChatModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.PersonalChatModel
import com.tmu.studybudd.model.student.StudentModel

interface ChatRepo {
    fun getPersonalConversationById(senderId: Int, receiverId: Int): List<PersonalChatModel>
    fun getGroupConversationById(id: Int): List<GroupChatModel>
    fun getChatHistoryById(id: Int): List<PersonalChatModel>
    fun groupMessageSend(groupId: Int, senderId: Int, text: String)
    fun personalMessageSend(otherStudentId: Int, myId: Int, text: String)
    fun getPersonalChatHistory(myId: Int): List<StudentModel>
    fun getGroupChatHistory(myId: Int): List<GroupModel>
    fun getStudentById(id: Int): StudentModel?

    fun getGroupById(id: Int): GroupModel?
}
