package com.example.denquizgame.game

data class QuestionAndChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
) {

}
