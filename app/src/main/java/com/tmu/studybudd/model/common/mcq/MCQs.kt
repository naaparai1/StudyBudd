package com.tmu.studybudd.model.common.mcq

import com.google.gson.annotations.SerializedName

data class MCQs(
    @SerializedName("topic") var topic: String = "",
    @SerializedName("questions") var questions: ArrayList<Question> = arrayListOf()
)
