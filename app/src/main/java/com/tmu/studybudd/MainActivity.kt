package com.tmu.studybudd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.ui.theme.StudyBuddTheme
import com.tmu.studybudd.view.CourseSelectionScreen
import com.tmu.studybudd.view.RouteConstants
import com.tmu.studybudd.view.common.AppContainer
import com.tmu.studybudd.view.home.student.StatusUpdateScreen
import com.tmu.studybudd.view.home.student.StudentHomeScreen
import com.tmu.studybudd.view.home.student.chat.GroupConversationScreen
import com.tmu.studybudd.view.home.student.chat.PersonalChatScreen
import com.tmu.studybudd.view.home.student.chatbox.ChatBoxScreen
import com.tmu.studybudd.view.home.student.practisequestions.PractiseQuestionsSetScreen
import com.tmu.studybudd.view.home.student.practisequestions.QuizApp
import com.tmu.studybudd.view.home.student.studentgroup.GroupScreen
import com.tmu.studybudd.view.home.student.studentgroup.StudentAndGroupScreen
import com.tmu.studybudd.view.home.student.studentgroup.StudentProfileScreen
import com.tmu.studybudd.view.home.teacher.TeacherHomeScreen
import com.tmu.studybudd.view.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyBuddTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyStudyBuddApp()
                }
            }
        }
    }
}

@Composable
fun MyStudyBuddApp() {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val loggedInStudent = viewModel.loggedInStudent
    val loggedInTeacher = viewModel.loggedInTeacher
    AppContainer(
        content = { onScreenChange ->
            MyNavHost(
                navController = navController,
                viewModel = viewModel,
                student = loggedInStudent,
                teacher = loggedInTeacher,
                onScreenChange
            )
        },
        onBackClick = { navController.navigateUp() }
    )
}

@Composable
private fun MyNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    student: StudentModel?,
    teacher: TeacherModel?,
    onScreenChange: (String, Boolean) -> Unit

) {
    NavHost(navController = navController, startDestination = RouteConstants.COURSE_SELECTION) {
        composable(RouteConstants.COURSE_SELECTION) {
            CourseSelectionScreen(
                nextScreen = {
                    viewModel.saveCourseId(it)
                    navController.navigate(RouteConstants.LOGIN + it)
                },
                onScreenChange = onScreenChange
            )
        }
        composable(
            RouteConstants.LOGIN + "{${RouteConstants.COURSE_ID}}",
            arguments = listOf(
                navArgument(RouteConstants.COURSE_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            LoginScreen(
                it.arguments?.getString(RouteConstants.COURSE_ID),
                openStudentHome = { student ->
                    viewModel.saveStudent(student)
                    navController.navigate(RouteConstants.STUDENT_HOME)
                },
                openTeacherHome = { teacher ->
                    viewModel.saveTeacher(teacher)
                    navController.navigate(RouteConstants.TEACHER_HOME)
                },
                onScreenChange = onScreenChange
            )
        }
        composable(RouteConstants.STUDENT_HOME) {
            student?.let { currentStudent ->
                StudentHomeScreen(
                    student = currentStudent,
                    onChangeStatus = {
                        navController.navigate(RouteConstants.CHANGE_STATUS)
                    },
                    navigateStudentGroup = {
                        navController.navigate(RouteConstants.STUDENT_GROUP_MAIN)
                    },
                    navigateChatBox = {
                        navController.navigate(RouteConstants.CHAT_BOX)
                    },
                    navigatePractiseQuestion = {
                        navController.navigate(RouteConstants.PRACTISE_SET)
                    },
                    onScreenChange = onScreenChange
                )
            }
        }
        composable(RouteConstants.CHANGE_STATUS) {
            student?.status?.let { status ->
                StatusUpdateScreen(
                    status,
                    onSave = {
                        viewModel.saveStatus(it)
                        navController.navigateUp()
                    },
                    onScreenChange = onScreenChange
                )
            }
        }
        composable(RouteConstants.STUDENT_GROUP_MAIN) {
            StudentAndGroupScreen(
                onStudentClicked = {
                    navController.navigate(RouteConstants.STUDENT_PROFILE + it.id)
                },
                onGroupClicked = {
                    navController.navigate(RouteConstants.GROUP_PROFILE + it.id)
                },
                onScreenChange = onScreenChange
            )
        }
        composable(
            RouteConstants.STUDENT_PROFILE + "{${RouteConstants.STUDENT_ID}}",
            arguments = listOf(
                navArgument(RouteConstants.STUDENT_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt(RouteConstants.STUDENT_ID)?.let { studentId ->
                StudentProfileScreen(
                    studentId,
                    onChatClicked = {
                        navController.navigate(RouteConstants.PERSONAL_CHAT + "$studentId")
                    },
                    onScreenChange = onScreenChange
                )
            }
        }
        composable(
            RouteConstants.GROUP_PROFILE + "{${RouteConstants.GROUP_ID}}",
            arguments = listOf(
                navArgument(RouteConstants.GROUP_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt(RouteConstants.GROUP_ID)?.let { groupId ->
                GroupScreen(
                    groupId,
                    onGroupChatClicked = {
                        navController.navigate(RouteConstants.GROUP_CHAT + "$groupId")
                    },
                    onScreenChange = onScreenChange
                )
            }
        }
        composable(
            RouteConstants.PERSONAL_CHAT + "{${RouteConstants.STUDENT_ID}}",
            arguments = listOf(
                navArgument(RouteConstants.STUDENT_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt(RouteConstants.STUDENT_ID)?.let { studentId ->
                PersonalChatScreen(studentId, onScreenChange)
            }
        }
        composable(
            RouteConstants.GROUP_CHAT + "{${RouteConstants.GROUP_ID}}",
            arguments = listOf(
                navArgument(RouteConstants.GROUP_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt(RouteConstants.GROUP_ID)?.let { groupId ->
                GroupConversationScreen(groupId, onScreenChange)
            }
        }
        composable(RouteConstants.PRACTISE_SET) {
            PractiseQuestionsSetScreen(
                onMCQClicked = {
                    navController.navigate(RouteConstants.MCQ + it)
                },
                onScreenChange = onScreenChange
            )
        }
        composable(
            RouteConstants.MCQ + "{${RouteConstants.PRACTISE_TOPIC}}",
            arguments = listOf(
                navArgument(RouteConstants.PRACTISE_TOPIC) {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString(RouteConstants.PRACTISE_TOPIC)?.let { topic ->
                QuizApp(
                    topic = topic,
                    navigateUp = {
                        navController.navigateUp()
                    },
                    onScreenChange = onScreenChange
                )
            }
        }
        composable(RouteConstants.CHAT_BOX) {
            ChatBoxScreen(
                onStudentClicked = {
                    navController.navigate(RouteConstants.PERSONAL_CHAT + "$it")
                },
                onGroupClicked = {
                    navController.navigate(RouteConstants.GROUP_CHAT + "$it")
                },
                onScreenChange = onScreenChange
            )
        }
        composable(RouteConstants.TEACHER_HOME) {
            teacher?.let { it1 ->
                TeacherHomeScreen(
                    teacher = it1,
                    onScreenChange = onScreenChange
                )
            }
        }
    }
}
