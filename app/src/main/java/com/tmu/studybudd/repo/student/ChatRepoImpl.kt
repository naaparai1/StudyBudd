package com.tmu.studybudd.repo.student

import com.tmu.studybudd.fakedb.FakeDB
import com.tmu.studybudd.model.student.GroupChatModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.PersonalChatModel
import com.tmu.studybudd.model.student.StudentModel
import kotlin.random.Random

object ChatRepoImpl : ChatRepo {
    override fun getPersonalConversationById(
        senderId: Int,
        receiverId: Int
    ): List<PersonalChatModel> {
        return FakeDB.personalChats.filter {
            (it.senderId == senderId && it.receiverId == receiverId) ||
                (it.receiverId == senderId && it.senderId == receiverId)
        }
    }

    override fun getGroupConversationById(id: Int): List<GroupChatModel> {
        return FakeDB.groupConversations.filter {
            it.groupId == id
        }
    }

    override fun getChatHistoryById(id: Int): List<PersonalChatModel> {
        return FakeDB.personalChats.filter {
            it.senderId == id || it.receiverId == id
        }
    }

    override fun groupMessageSend(groupId: Int, senderId: Int, text: String) {
        val conversationId = Random.nextInt(100, 10000)
        val timeStamp = System.currentTimeMillis()
        val groupConversation =
            GroupChatModel(conversationId, groupId, senderId, text, timeStamp)
        FakeDB.groupConversations.add(groupConversation)
    }

    override fun personalMessageSend(otherStudentId: Int, myId: Int, text: String) {
        val conversationId = Random.nextInt(100, 10000)
        val timeStamp = System.currentTimeMillis()
        val conversation =
            PersonalChatModel(conversationId, myId, otherStudentId, text, timeStamp)
        FakeDB.personalChats.add(conversation)
    }

    override fun getPersonalChatHistory(myId: Int): List<StudentModel> {
        val myConversations = FakeDB.personalChats.filter {
            it.senderId == myId || it.receiverId == myId
        }
        val students = mutableSetOf<StudentModel>()
        myConversations.forEach {
            val student = getStudentById(it.senderId)
            student?.let { it1 -> students.add(it1) }
        }
        students.removeIf {
            it.id == myId
        }
        return students.toList()
    }

    override fun getStudentById(id: Int): StudentModel? {
        return FakeDB.students.firstOrNull() { it.id == id }
    }

    override fun getGroupById(id: Int): GroupModel? {
        return FakeDB.groups.firstOrNull() { it.id == id }
    }

    override fun getGroupChatHistory(myId: Int): List<GroupModel> {
        val myConversations = FakeDB.groupConversations.filter {
            it.senderId == myId
        }
        val groups = mutableSetOf<GroupModel>()
        myConversations.forEach {
            val group = getGroupById(it.senderId)
            group?.let { it1 -> groups.add(it1) }
        }
        return groups.toList()
    }
}
