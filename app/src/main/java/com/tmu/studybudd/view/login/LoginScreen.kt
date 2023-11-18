package com.tmu.studybudd.view.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmu.studybudd.model.student.StudentModel
import com.tmu.studybudd.model.teacher.TeacherModel
import com.tmu.studybudd.util.Utils

@Composable
fun LoginScreen(
    courseID: String?,
    openStudentHome: (StudentModel) -> Unit,
    openTeacherHome: (TeacherModel) -> Unit,
    onScreenChange: (String, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val validCredential: (String, String) -> StudentModel? = { e, p ->
        viewModel.validStudentCredential(e, p)
    }
    val validTeacherCredential: (String, String) -> TeacherModel? = { e, p ->
        viewModel.validateTeacherCredential(e, p)
    }
    onScreenChange("Login", true)
    LoginScreenStateless(
        courseID = courseID ?: "",
        validStudentCredential = validCredential,
        onStudentLogin = {
            openStudentHome(it)
        },
        validateTeacherCredential = validTeacherCredential,
        onTeacherLogin = {
            openTeacherHome(it)
        }
    )
}

@Composable
fun LoginScreenStateless(
    courseID: String,
    validStudentCredential: (email: String, password: String) -> StudentModel?,
    onStudentLogin: (StudentModel) -> Unit,
    validateTeacherCredential: (email: String, password: String) -> TeacherModel?,
    onTeacherLogin: (TeacherModel) -> Unit
) {
    var emailId by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidthHalf = (.5 * configuration.screenWidthDp).dp
    var checkedState by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Course Selected: $courseID")
        Spacer(modifier = Modifier.size(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                modifier = Modifier.width(screenWidthHalf),
                value = emailId,
                onValueChange = { newText -> emailId = newText },
                label = { Text("Enter Email") },

                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),

                textStyle = MaterialTheme.typography.bodyMedium
            )
            Text(text = "@torontomu.ca", modifier = Modifier.weight(1f), maxLines = 1)
        }
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = passWord,
            onValueChange = { newText -> passWord = newText },
            label = { Text("Enter Password") },
            visualTransformation = PasswordVisualTransformation(),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(16.dp))
        MyCheckBox(checkedState) {
            checkedState = it
        }
        Button(onClick = {
            val email = "$emailId@torontomu.ca"

            if (Utils.isValidEmail(email) && (Utils.isPassWordValid(passWord))) {
                if (checkedState) {
                    val teacher = validateTeacherCredential(email, passWord)
                    if (teacher != null) {
                        onTeacherLogin(teacher)
                    } else {
                        Toast.makeText(context, "Teacher does not exist", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    val studentModel = validStudentCredential(email, passWord)
                    if (studentModel != null) {
                        onStudentLogin(studentModel)
                    } else {
                        Toast.makeText(context, "Student does not exist", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(context, "Enter Correct UserID or Password", Toast.LENGTH_SHORT)
                    .show()
            }
        }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun MyCheckBox(checkedState: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Checkbox
        Checkbox(
            checked = checkedState,
            onCheckedChange = {
                onCheckedChange(it)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            ),
            modifier = Modifier
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Are you a teacher?")
    }
}
