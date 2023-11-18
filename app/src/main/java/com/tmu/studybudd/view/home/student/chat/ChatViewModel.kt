package com.tmu.studybudd.view.home.student.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tmu.studybudd.model.student.GroupChatModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.PersonalChatModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.repo.student.ChatRepo
import com.tmu.studybudd.repo.student.PreferenceRepo
import com.tmu.studybudd.repo.student.StudentAndGroupRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo,
    private val studentAndGroupRepo: StudentAndGroupRepo,
    private val preferenceRepo: PreferenceRepo
) : ViewModel() {
    var personalChats by mutableStateOf<List<PersonalChatModel>>(emptyList())
    var groupChats by mutableStateOf<List<GroupChatModel>>(emptyList())
    var personalChatHistory by mutableStateOf<List<StudentModel>>(emptyList())
    var groupChatHistory by mutableStateOf<List<GroupModel>>(emptyList())
    var otherUser by mutableStateOf("")
    var groupName by mutableStateOf("")
    private var myId = preferenceRepo.getStudent()?.id

    init {
        getPersonalChatBox()
        getGroupChatBox()
    }

    fun getStudentId(id: Int) = studentAndGroupRepo.getStudentById(id)

    fun fetchPersonalChats(id: Int) {
        val studentModel = getStudentId(id)
        otherUser = studentModel?.name ?: ""
        val student = preferenceRepo.getStudent()
        student?.id?.let {
            personalChats = chatRepo.getPersonalConversationById(student.id, id)
        }
    }

    fun isSameStudent(id: Int): Boolean {
        val student = preferenceRepo.getStudent()
        return student?.id == id
    }

    fun getGroupById(id: Int) = studentAndGroupRepo.getGroupById(id)

    fun fetchGroupChats(id: Int) {
        val group = getGroupById(id)
        groupName = group?.name ?: ""
        groupChats = chatRepo.getGroupConversationById(id)
    }

    fun onGroupTextSend(text: String, id: Int) {
        myId?.let {
            chatRepo.groupMessageSend(id, it, text)
            fetchGroupChats(id)
        }
    }

    fun onPersonalChatSend(text: String, otherStudentId: Int) {
        myId?.let {
            chatRepo.personalMessageSend(otherStudentId, it, text)
            fetchPersonalChats(otherStudentId)
        }
    }

    private fun getPersonalChatBox() {
        myId?.let {
            personalChatHistory = chatRepo.getPersonalChatHistory(it)
        }
    }

    private fun getGroupChatBox() {
        myId?.let {
            groupChatHistory = chatRepo.getGroupChatHistory(it)
        }
    }
}
