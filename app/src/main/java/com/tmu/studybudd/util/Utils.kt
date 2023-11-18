package com.tmu.studybudd.util

object Utils {
    fun isCourseIdValid(id: String): Boolean {
        return id.matches(Regex("^.{6}$"))
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return email.matches(emailRegex)
    }

    fun isPassWordValid(password: String): Boolean {
        return password.length in 6..19
    }
}
