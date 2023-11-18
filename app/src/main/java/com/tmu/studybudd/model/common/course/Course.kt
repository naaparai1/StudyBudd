package com.tmu.studybudd.model.common.course

import com.google.gson.annotations.SerializedName

data class Course(

    @SerializedName("courseCode") var courseCode: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("creditHours") var creditHours: Int? = null

)
