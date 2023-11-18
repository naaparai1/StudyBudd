package com.tmu.studybudd.model.common.mcq

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question") var question: String? = null,
    @SerializedName("options") var options: ArrayList<String> = arrayListOf(),
    @SerializedName("correctAnswer") var correctAnswer: Int? = null
)
