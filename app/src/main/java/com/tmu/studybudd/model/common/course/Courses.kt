package com.tmu.studybudd.model.common.course

import com.google.gson.annotations.SerializedName

data class Courses(

    @SerializedName("courses") var courses: ArrayList<Course> = arrayListOf()

)
