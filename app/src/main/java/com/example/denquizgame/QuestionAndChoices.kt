package com.example.denquizgame

data class QuestionAndChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
) {

}
