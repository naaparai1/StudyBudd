package com.tmu.studybudd.fakedb

import android.content.Context
import com.google.gson.Gson
import com.tmu.studybudd.model.common.course.Course
import com.tmu.studybudd.model.common.course.Courses
import com.tmu.studybudd.model.common.mcq.MCQs
import com.tmu.studybudd.model.student.GroupChatModel
import com.tmu.studybudd.model.student.GroupModel
import com.tmu.studybudd.model.student.PersonalChatModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

object FakeDB {
    lateinit var teachers: List<TeacherModel>
    val courses = mutableListOf<Course>()
    lateinit var mcqs: List<MCQs>

    // fake students
    val student1 =
        StudentModel(1, "Chandler", "chandler@torontomu.ca", "I am studying Java")
    val student2 = StudentModel(2, "Rachel", "rachel@torontomu.ca", "Into fashion")
    val student3 = StudentModel(3, "Rose", "rose@torontomu.ca", "World of Dinosaurs")
    val student4 =
        StudentModel(4, "Monica", "monica@torontomu.ca", "Anybody up for bakery?")
    val student5 = StudentModel(5, "Phoeboe", "phoeboe@torontomu.ca", "Lost in psychology")
    val student6 = StudentModel(6, "Joey", "joey@torontomu.ca", "How you doing?")
    val students = listOf(student1, student2, student3, student4, student5, student6)

    // fake groups
    val group1 = GroupModel(1, "DBMS", "Learning and growing", students)
    val group2 = GroupModel(2, "Java and OOPS", "Last few chapters", students)
    val group3 = GroupModel(3, "Data structure", "Learn the scratch", students)
    val group4 = GroupModel(4, "A.I", "Learning and growing", students)
    val group5 = GroupModel(5, "Mobile development", "iOS and Android", students)
    val groups = listOf(group1, group2, group3, group4, group5)

    // fake conversation
    private val conversationModel1 =
        PersonalChatModel(1, 1, 2, "Hi There", System.currentTimeMillis())
    private val conversationModel2 =
        PersonalChatModel(2, 2, 1, "Hi There", System.currentTimeMillis())
    private val conversationModel3 =
        PersonalChatModel(3, 1, 3, "Hi There", System.currentTimeMillis())
    private val conversationModel4 =
        PersonalChatModel(4, 3, 1, "Hi There", System.currentTimeMillis())
    private val conversationModel5 =
        PersonalChatModel(5, 3, 1, "Hi There", System.currentTimeMillis())
    private val conversationModel6 =
        PersonalChatModel(6, 1, 4, "Hi There", System.currentTimeMillis())
    private val conversationModel7 =
        PersonalChatModel(7, 1, 4, "Hi There", System.currentTimeMillis())
    private val conversationModel8 =
        PersonalChatModel(8, 5, 1, "Hi There", System.currentTimeMillis())
    private val conversationModel9 =
        PersonalChatModel(9, 1, 6, "Hi There", System.currentTimeMillis())

    val personalChats = mutableListOf(
        conversationModel1,
        conversationModel2,
        conversationModel3,
        conversationModel4,
        conversationModel5,
        conversationModel6,
        conversationModel7,
        conversationModel8,
        conversationModel9
    )

    // group conversation
    private val groupChatModel1 =
        GroupChatModel(1, 1, 1, "Hi DBMS C", System.currentTimeMillis())
    private val groupChatModel2 =
        GroupChatModel(2, 1, 2, "Hi DBMS Ra", System.currentTimeMillis())
    private val groupChatModel3 =
        GroupChatModel(3, 1, 3, "Hi DBMS Ro", System.currentTimeMillis())
    private val groupChatModel4 =
        GroupChatModel(4, 1, 4, "Hi DBMS M", System.currentTimeMillis())
    private val groupChatModel5 =
        GroupChatModel(5, 1, 5, "Hi DBMS P", System.currentTimeMillis())
    private val groupChatModel6 =
        GroupChatModel(6, 1, 6, "Hi DBMS J", System.currentTimeMillis())
    val groupConversations = mutableListOf(
        groupChatModel1,
        groupChatModel2,
        groupChatModel3,
        groupChatModel4,
        groupChatModel5,
        groupChatModel6
    )

    suspend fun loadFakeDatas(context: Context) {
        withContext(IO) {
            loadmcqs(context)
            loadCourses(context)
        }
    }

    private fun loadCourses(context: Context) {
        val coursesString: String =
            context.assets.open("courses.json").bufferedReader().use { it.readText() }
        val courses: Courses = Gson().fromJson(coursesString, Courses::class.java)
        this.courses.addAll(courses.courses)
        loadTeachers()
    }

    private fun loadTeachers() {
        val teacher1 = TeacherModel(1, "John", "john@torontomu.ca", courses.subList(0, 2))
        val teacher2 = TeacherModel(2, "Michael", "michael@torontomu.ca", courses.subList(3, 6))
        val teacher3 = TeacherModel(3, "Dwight", "dwight@torontomu.ca", courses.subList(7, 8))
        teachers = listOf(teacher1, teacher2, teacher3)
    }

    private fun loadmcqs(context: Context) {
        val dbmsJsonString: String =
            context.assets.open("dbms_mcq.json").bufferedReader().use { it.readText() }
        val dataStructureJsonString: String =
            context.assets.open("datastructure_mcq.json").bufferedReader().use { it.readText() }
        val dbmsMcqs: MCQs = Gson().fromJson(dbmsJsonString, MCQs::class.java)
        val dataStructureMcqs: MCQs = Gson().fromJson(dataStructureJsonString, MCQs::class.java)
        mcqs = listOf(dbmsMcqs, dataStructureMcqs)
    }
}
